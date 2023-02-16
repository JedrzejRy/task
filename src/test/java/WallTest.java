import org.example.ComplexBlock;
import org.example.SimpleBlock;
import org.example.Wall;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WallTest {

    private static final SimpleBlock BLOCK1 = new SimpleBlock("White", "Wood");
    private static final SimpleBlock BLOCK2 = new SimpleBlock("Black", "Metal");
    private static final SimpleBlock BLOCK3 = new SimpleBlock("Red", "Ceramics");
    private static final SimpleBlock BLOCK4 = new SimpleBlock("Yellow", "Plastic");
    private static final SimpleBlock BLOCK5 = new SimpleBlock("Purple", "Metal");
    private static final SimpleBlock BLOCK6 = new SimpleBlock("White", "Stone");
    private static final ComplexBlock COMPLEX_BLOCK = new ComplexBlock("Green", "Marble");
    private static final ComplexBlock COMPLEX_BLOCK2 = new ComplexBlock("Orange", "Wood");
    private static final ComplexBlock COMPLEX_BLOCK3 = new ComplexBlock("Blue", "Onyx");

    private Wall emptyWall;
    private Wall filledWall;

    @BeforeAll
    static void setUpComplexBricks() {
        COMPLEX_BLOCK.addBlock(BLOCK2);

        COMPLEX_BLOCK2.addBlock(COMPLEX_BLOCK);
        COMPLEX_BLOCK2.addBlock(BLOCK3);
        COMPLEX_BLOCK2.addBlock(BLOCK4);

        COMPLEX_BLOCK3.addBlock(BLOCK5);
    }

    @BeforeEach
    void setUpWalls() {
        emptyWall = new Wall();
        filledWall = new Wall(BLOCK1, COMPLEX_BLOCK2, BLOCK6, COMPLEX_BLOCK3);
    }

    @Test
    void shouldInstantiateWalls() {
        assertThat(emptyWall).isNotNull();
        assertThat(filledWall).isNotNull();
    }

    @Test
    void shouldReturnEmptyOptionalWhenNotFoundByColorAndWallIsEmpty() {
        assertThat(emptyWall.findBlockByColor("Black").isEmpty()).isTrue();
    }

    @Test
    void shouldReturnEmptyListWhenNotFoundByMaterialAndWallIsEmpty() {
        assertThat(emptyWall.findBlocksByMaterial("Stone").isEmpty()).isTrue();
    }

    @Test
    void shouldReturn0WhenWallIsEmpty() {
        assertThat(emptyWall.count()).isEqualTo(0);
    }

    @Test
    void shouldReturnEmptyOptionalWhenNotFoundByColorAndWallIsFilled() {
        assertThat(filledWall.findBlockByColor("Brown").isPresent()).isFalse();
    }

    @Test
    void shouldReturnEmptyListWhenNotFoundByMaterialAndWallIsFilled() {
        assertThat(filledWall.findBlocksByMaterial("Glass").isEmpty()).isTrue();
    }

    @Test
    void shouldProperlyCountBricks() {
        assertThat(filledWall.count()).isEqualTo(9);
    }

    @Test
    void shouldFindAnyBrickWhenFoundByColorAndWallIsFilled() {
        assertThat(filledWall.findBlockByColor("White").isPresent()).isTrue();
    }

    @Test
    void shouldFindListOf2BricksWhenFoundByMaterialAndWallIsFilled() {
        assertThat(filledWall.findBlocksByMaterial("Metal").size()).isEqualTo(2);
    }

    @Test
    void shouldFindComplexBlockByColor() {
        assertThat(filledWall.findBlockByColor("Orange").get()).isEqualTo(COMPLEX_BLOCK2);
    }

    @Test
    void shouldFindComplexBlockByMaterial() {
        assertThat(filledWall.findBlocksByMaterial("Onyx").contains(COMPLEX_BLOCK3)).isTrue();
    }

    @Test
    void shouldFindNestedBlockByColor() {
        assertThat(filledWall.findBlockByColor("Red").get()).isEqualTo(BLOCK3);
    }

    @Test
    void shouldReturnListOfNestedBlocksByMaterial() {
        assertThat(filledWall.findBlocksByMaterial("Plastic").contains(BLOCK4)).isTrue();
    }

    @Test
    void shouldAcceptNullAsColor() {
        assertThrows(IllegalArgumentException.class, () -> emptyWall.findBlockByColor(null));
    }

    @Test
    void shouldAcceptNullAsMaterial() {
        assertThrows(IllegalArgumentException.class, () -> filledWall.findBlockByColor(null));
    }

    @Test
    void shouldReturnListOfAllNestedBlocksInComplexBlock(){
        assertThat(COMPLEX_BLOCK2.getBlocks().size()).isEqualTo(3);
    }

}
