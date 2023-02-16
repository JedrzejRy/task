package org.example;

import java.util.stream.Stream;

public interface Block {
    String getColor();
    String getMaterial();

    /**
     * Create a stream out of block
     */
    Stream<Block> toStream();
}
