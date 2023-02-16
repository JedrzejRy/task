package org.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure{

    private final List<Block> blocks = new LinkedList<>();

    /**
     * Constructor allows adding new blocks to the Wall
     */
    public Wall(Block... blocks) {
        Arrays.stream(blocks).forEach(this::addBlock);
    }


    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color == null){
            throw new IllegalArgumentException("color is null");
        }
       return createStreamOfAllBlocks().filter(block -> block.getColor().equals(color)).findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null){
            throw new IllegalArgumentException("material is null");
        }
        return createStreamOfAllBlocks().filter(block -> block.getMaterial().equals(material)).collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int) createStreamOfAllBlocks().count();
    }

    private Stream<Block> createStreamOfAllBlocks(){
        return blocks.stream().flatMap(Block::toStream);
    }

    public void addBlock(Block block){
        blocks.add(block);
    }


}
