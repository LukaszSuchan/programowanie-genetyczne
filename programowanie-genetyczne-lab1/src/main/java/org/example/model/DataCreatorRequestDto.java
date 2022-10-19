package org.example.model;

public record DataCreatorRequestDto(
        int numberOfConst,
        int constStartRange,
        int constEndRange,
        double rangeStart,
        double rangeEnd,
        double step,
        String fileName

) {
}
