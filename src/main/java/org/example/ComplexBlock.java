package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ComplexBlock extends SimpleBlock implements CompositeBlock{
    /**
     * List of nested blocks in ComplexBlock
     */
    private final List<Block> blocks = new LinkedList<>();

    public ComplexBlock(String color, String material) {
        super(color, material);
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    public void addBlock(Block block){
        blocks.add(block);
    }

    /**
     * Create a stream out of complex block taking into account nested blocks.
     */

    @Override
    public Stream<Block> toStream() {
        return Stream.concat(super.toStream(), blocks.stream().flatMap(Block::toStream));
    }
}
