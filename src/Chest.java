/**
 * Chest.java - Interface to chests.
 *
 * @author James
 */
public class Chest extends BaseContainerBlock<OTileEntityChest> implements ComplexBlock {

    public Chest(OTileEntityChest chest) {
        this(null, chest);
    }
    public Chest(OContainer oContainer, OTileEntityChest chest) {
        super(oContainer, chest, "Chest");
    }

    public DoubleChest findAttachedChest() {
        Block block = getBlock();

        DoubleChest result;

        result = tryAttachedChest(block, Block.Face.Front);
        if (result != null) {
            return result;
        }

        result = tryAttachedChest(block, Block.Face.Back);
        if (result != null) {
            return result;
        }

        result = tryAttachedChest(block, Block.Face.Left);
        if (result != null) {
            return result;
        }

        result = tryAttachedChest(block, Block.Face.Right);
        if (result != null) {
            return result;
        }

        return null;
    }

    private DoubleChest tryAttachedChest(Block origin, Block.Face face) {
        Block block = origin.getFace(face);

        if (block.blockType == Block.Type.Chest) {
            ComplexBlock cblock = getWorld().getOnlyComplexBlock(block);

            if ((cblock != null) && (cblock instanceof Chest)) {
                Chest chest = (Chest) cblock;

                return new DoubleChest(getOContainer(), new OInventoryLargeChest(getName(), container, chest.container));
            }
        }

        return null;
    }
}
