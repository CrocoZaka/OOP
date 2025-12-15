package ru.nsu.ekovalenko4.markdown;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Markdown table element.
 */
public final class Table extends Element {

    public static final int ALIGN_LEFT = -1;
    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_RIGHT = 1;

    private final List<Text> header;
    private final List<Integer> alignments;
    private final List<List<Text>> rows;
    private final int rowLimit;

    private Table(Builder builder) {
        this.header = List.copyOf(builder.header);
        this.alignments = List.copyOf(builder.alignments);
        this.rows = builder.rows.stream()
                .map(List::copyOf)
                .toList();
        this.rowLimit = builder.rowLimit;
    }

    @Override
    public String toMarkdown() {
        StringBuilder sb = new StringBuilder();
        appendRow(sb, header);
        sb.append("|");
        for (int i = 0; i < header.size(); i++) {
            int a = alignments.get(i);
            sb.append(" ").append(alignmentMarker(a)).append(" |");
        }
        sb.append("\n");

        int written = 0;
        for (List<Text> row : rows) {
            if (rowLimit > 0 && written >= rowLimit) break;
            appendRow(sb, row);
            written++;
        }

        return sb.toString().trim();
    }

    private void appendRow(StringBuilder sb, List<Text> cells) {
        sb.append("| ");
        for (int i = 0; i < header.size(); i++) {
            Text cell = i < cells.size()
                    ? cells.get(i)
                    : Text.builder("").build();
            sb.append(cell.toMarkdown()).append(" | ");
        }
        sb.setLength(sb.length() - 1);
        sb.append("\n");
    }

    private static String alignmentMarker(int alignment) {
        return switch (alignment) {
            case ALIGN_LEFT -> ":---";
            case ALIGN_CENTER -> ":---:";
            case ALIGN_RIGHT -> "---:";
            default -> throw new IllegalArgumentException("Unknown alignment: " + alignment);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Table table)) {
            return false;
        }
        return rowLimit == table.rowLimit
                && header.equals(table.header)
                && alignments.equals(table.alignments)
                && rows.equals(table.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, alignments, rows, rowLimit);
    }

    public static final class Builder {

        private final List<Text> header = new ArrayList<>();
        private final List<Integer> alignments = new ArrayList<>();
        private final List<List<Text>> rows = new ArrayList<>();
        private int rowLimit = 0;
        private boolean headerSet = false;

        public Builder withAlignments(int... alignments) {
            this.alignments.clear();
            for (int a : alignments) {
                this.alignments.add(a);
            }
            return this;
        }

        public Builder withRowLimit(int limit) {
            this.rowLimit = limit;
            return this;
        }

        public Builder addRow(Text... cells) {
            List<Text> row = List.of(cells);

            if (!headerSet) {
                header.addAll(row);
                headerSet = true;
            } else {
                rows.add(row);
            }
            return this;
        }

        public Builder addRow(Object... cells) {
            Text[] converted = new Text[cells.length];
            for (int i = 0; i < cells.length; i++) {
                converted[i] = convert(cells[i]);
            }
            return addRow(converted);
        }

        private Text convert(Object o) {
            if (o instanceof Text t) {
                return t;
            }
            return Text.builder(String.valueOf(o)).build();
        }

        private void validate() {
            if (!headerSet) {
                throw new IllegalArgumentException("Table must have a header row");
            }
            if (!alignments.isEmpty() && alignments.size() != header.size()) {
                throw new IllegalArgumentException("Alignments count must match columns count");
            }
            if (alignments.isEmpty()) {
                for (int i = 0; i < header.size(); i++) {
                    alignments.add(ALIGN_LEFT);
                }
            }
        }

        public Table build() {
            validate();
            return new Table(this);
        }
    }
}