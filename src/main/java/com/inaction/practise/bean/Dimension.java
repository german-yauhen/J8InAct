package com.inaction.practise.bean;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Dimension {

    W960H540(960, 520), W1024H576(1024, 576), W1280H720(1280, 720), W1366H768(1366, 768),
    W1600H900(1600, 900), W1920H1080(1920, 1080), W2048H1152(2048, 1152), W2400H1350(2400, 1350),
    W2560H1440(2560, 1440), W2880H1620(2880, 1620), W3554H1999(3554, 1999), W3840H2160(3840, 2160),
    W5120H2880(5120, 2880), W7680H4320(7680, 4320);

    private Integer width;
    private Integer height;

    public static List<Dimension> dimensions() {
        return Arrays.asList(Dimension.values());
    }

}
