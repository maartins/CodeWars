package com.liftdgame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Encoder {
    public String compress(int[] raw) {
        if (raw == null || raw.length == 0)
            return "";

        Note simpleNote = null;
        List<Note> notes = new ArrayList<>();

        for (int i : raw) {
            if (simpleNote == null) {
                simpleNote = new SimpleNote(i);
            } else {
                simpleNote = simpleNote.setNext(new SimpleNote(i));
            }
            notes.add(simpleNote);
        }

        List<CompressedNote> identicalNotes = walkThroughIdentical(notes.get(0));

        for (CompressedNote compress : identicalNotes) {
            int firstIndex = notes.indexOf(compress.getFirst());
            int lastIndex = notes.indexOf(compress.getLast());

            if (lastIndex >= firstIndex)
                notes.subList(firstIndex, lastIndex + 1).clear();

            if (firstIndex < notes.size())
                compress.setNext(notes.get(firstIndex));
            if (firstIndex - 1 >= 0)
                notes.get(firstIndex - 1).setNext(compress);

            notes.add(firstIndex, compress);
        }

        List<CompressedNote> consecutiveNotes = walkThroughConsecutive(notes.get(0));

        for (CompressedNote compress : consecutiveNotes) {
            int firstIndex = notes.indexOf(compress.getFirst());
            int lastIndex = notes.indexOf(compress.getLast());

            if (lastIndex >= firstIndex) {
                notes.subList(firstIndex, lastIndex + 1).clear();
            }

            if (firstIndex < notes.size())
                compress.setNext(notes.get(firstIndex));
            if (firstIndex - 1 > 0)
                notes.get(firstIndex - 1).setNext(compress);

            notes.add(firstIndex, compress);
        }

        return notes.stream().map(Note::display).collect(Collectors.joining(","));
    }

    public static List<CompressedNote> walkThroughIdentical(Note initial) {
        int identicalCounter = 0;

        Note nextSimpleNote = initial;
        Note firstSimpleNote = null;
        Note lastSimpleNote;

        List<CompressedNote> result = new ArrayList<>();

        while (nextSimpleNote.getNext() != null) {
            if (nextSimpleNote.getValue() == nextSimpleNote.getNext().getValue()) {
                if (identicalCounter == 0)
                    firstSimpleNote = nextSimpleNote;
                identicalCounter++;
            } else {
                if (identicalCounter >= 1) {
                    lastSimpleNote = nextSimpleNote;
                    result.add(new IdenticalNote(firstSimpleNote, lastSimpleNote, identicalCounter + 1));
                }
                identicalCounter = 0;
            }

            nextSimpleNote = nextSimpleNote.getNext();
        }

        if (identicalCounter >= 1) {
            lastSimpleNote = nextSimpleNote;
            result.add(new IdenticalNote(firstSimpleNote, lastSimpleNote, identicalCounter + 1));
        }

        return result;
    }

    public static List<CompressedNote> walkThroughConsecutive(Note initial) {
        Note nextSimpleNote = initial;
        Note firstSimpleNote = nextSimpleNote;
        Note lastSimpleNote = null;

        int consecutiveCounter = 0;
        int previousInterval = nextSimpleNote.getNext() != null ? Math.abs(nextSimpleNote.getValue()) - Math.abs(nextSimpleNote.getNext().getValue()) : 0;

        List<CompressedNote> result = new ArrayList<>();

        while (nextSimpleNote.getNext() != null) {
            int currentInterval = nextSimpleNote.getValue() - nextSimpleNote.getNext().getValue();

            if (currentInterval != previousInterval) {
                if (consecutiveCounter >= 2) {
                    result.add(new ConsecutiveNote(firstSimpleNote, lastSimpleNote, Math.abs(previousInterval)));
                    firstSimpleNote = nextSimpleNote.getNext();
                    currentInterval = 9999;
                } else {
                    firstSimpleNote = nextSimpleNote;
                }
                consecutiveCounter = 1;
            } else {
                consecutiveCounter++;
                lastSimpleNote = nextSimpleNote.getNext() != null ? nextSimpleNote.getNext() : nextSimpleNote;
            }

            nextSimpleNote = nextSimpleNote.getNext();
            previousInterval = currentInterval;
        }

        if (consecutiveCounter >= 2) {
            result.add(new ConsecutiveNote(firstSimpleNote, lastSimpleNote, Math.abs(previousInterval)));
        }

        return result;
    }

    public interface Note {
        String display();
        int getValue();
        Note getNext();
        Note setNext(Note next);
    }

    public interface CompressedNote extends Note {
         Note getFirst();
         Note getLast();
    }

    public static class SimpleNote implements Note {
        private final int value;
        private Note next;

        public SimpleNote(int value) {
            this.value = value;
        }

        @Override
        public String display() {
            return value + "";
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public Note getNext() {
            return next;
        }

        public Note setNext(Note next) {
            this.next = next;
            return next;
        }
    }

    public static class ConsecutiveNote implements CompressedNote {
        private final String value;
        private final Note first;
        private final Note last;
        private Note next;

        private int interval;

        public ConsecutiveNote(Note first, Note last, int interval) {
            this.first = first;
            this.last = last;
            this.interval = interval;

            if (interval > 1)
                value = first.getValue() + "-" + last.getValue() + "/" + interval;
            else
                value = first.getValue() + "-" + last.getValue();
        }

        @Override
        public String display() {
            return value;
        }

        @Override
        public int getValue() {
            int result = 0;
            int maxValue = last.getValue();
            int step = first.getValue();

            while (step != maxValue) {
                result += step;
                step += interval;
            }

            return 9999; // :(
        }

        @Override
        public Note getNext() {
            return next;
        }

        public Note setNext(Note next) {
            this.next = next;
            return next;
        }

        @Override
        public Note getFirst() {
            return first;
        }

        @Override
        public Note getLast() {
            return last;
        }
    }

    public static class IdenticalNote implements CompressedNote {
        private final String value;
        private final Note first;
        private final Note last;
        private Note next;
        private int count;

        public IdenticalNote(Note first, Note last, int count) {
            this.first = first;
            this.last = last;
            this.count = count;
            value = first.getValue() + "*" + count;
        }

        @Override
        public String display() {
            return value;
        }

        @Override
        public int getValue() {
            return 9999; // first.getValue() * count :(
        }

        @Override
        public Note getNext() {
            return next;
        }

        public Note setNext(Note next) {
            this.next = next;
            return next;
        }

        @Override
        public Note getFirst() {
            return first;
        }

        @Override
        public Note getLast() {
            return last;
        }
    }
}
