package org.example.model;

public record DataCreatorRequestDto(
        int numberOfConst,
        int constStartRange,
        int constEndRange,
        int rangeStart,
        int rangeEnd,
        double step,
        String fileName

) {
}
