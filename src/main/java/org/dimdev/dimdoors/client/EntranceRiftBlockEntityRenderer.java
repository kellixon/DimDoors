package org.dimdev.dimdoors.client;

import java.util.List;
import java.util.stream.IntStream;

import com.google.common.collect.ImmutableList;
import org.dimdev.dimdoors.block.entity.EntranceRiftBlockEntity;
import org.dimdev.dimdoors.util.RGBA;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Matrix4f;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class EntranceRiftBlockEntityRenderer extends BlockEntityRenderer<EntranceRiftBlockEntity> {
    public EntranceRiftBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
        super(blockEntityRenderDispatcher);
    }

    @Override
    public void render(EntranceRiftBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        List<RenderLayer> layers = IntStream.range(0, 16).mapToObj((i) -> {
            return MyRenderLayer.getDimensionalPortal(i + 1, blockEntity);
        }).collect(ImmutableList.toImmutableList());
        matrices.push();
        if (MinecraftClient.getInstance().world == null) {
            return;
        }
        Direction orientation = blockEntity.getOrientation();
        Vector3f vec = orientation.getOpposite().getUnitVector();
        this.renderVertices(blockEntity, matrices, vertexConsumers, orientation, vec, layers);

//        Vec3d offset = new Vec3d(vec);
//        DimensionalPortalRenderer.renderDimensionalPortal(
//                blockEntity.getPos().getX() + offset.x,
//                blockEntity.getPos().getY() + offset.y,
//                blockEntity.getPos().getZ() + offset.z,
//                //blockEntity.orientation.getHorizontalAngle(),
//                //blockEntity.orientation.getDirectionVec().getY() * 90,
//                orientation,
//                16,
//                16,
//                blockEntity.getColors(16),
//                matrices,
//                vertexConsumers.getBuffer(LAYERS.get(0)));
        matrices.pop();
    }

    private void renderVertices(EntranceRiftBlockEntity entrance, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Direction orientation, Vector3f vec, List<RenderLayer> layers) {
        vec.scale((float) (orientation == Direction.NORTH || orientation == Direction.WEST || orientation == Direction.UP ? 0.01 : 0.01 - 1));
        double d = entrance.getPos().getSquaredDistance(this.dispatcher.camera.getPos(), true);
        int k = this.getOffset(d);
        float g = 0.75F;
        Matrix4f matrix4f = matrices.peek().getModel();
        this.drawAllVertices(entrance, g, 0.15F, matrix4f, vertexConsumers.getBuffer(layers.get(0)), orientation);

        for(int l = 1; l < k; ++l) {
            this.drawAllVertices(entrance, g, 2.0F / (float)(18 - l), matrix4f, vertexConsumers.getBuffer(layers.get(l)), orientation);
        }
    }

    protected int getOffset(double d) {
        if (d > 36864.0D) {
            return 1;
        } else if (d > 25600.0D) {
            return 3;
        } else if (d > 16384.0D) {
            return 5;
        } else if (d > 9216.0D) {
            return 7;
        } else if (d > 4096.0D) {
            return 9;
        } else if (d > 1024.0D) {
            return 11;
        } else if (d > 576.0D) {
            return 13;
        } else {
            return d > 256.0D ? 14 : 15;
        }
    }

    private void drawAllVertices(EntranceRiftBlockEntity blockEntity, float u, float v, Matrix4f matrix4f, VertexConsumer vertexConsumer, Direction dir) {
        RGBA[] colors = MyRenderLayer.getColors(6);
        for (RGBA color : colors) {
            float red = color.getRed();
            float green = color.getGreen();
            float blue = color.getBlue();
            this.drawVertices(blockEntity, matrix4f, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, red, green, blue, Direction.SOUTH);
            this.drawVertices(blockEntity, matrix4f, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, red, green, blue, Direction.NORTH);
            this.drawVertices(blockEntity, matrix4f, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, red, green, blue, Direction.EAST);
            this.drawVertices(blockEntity, matrix4f, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, red, green, blue, Direction.WEST);
            this.drawVertices(blockEntity, matrix4f, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, red, green, blue, Direction.DOWN);
            this.drawVertices(blockEntity, matrix4f, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, red, green, blue, Direction.UP);
        }
    }

    private void drawVertices(EntranceRiftBlockEntity endPortalBlockEntity, Matrix4f matrix4f, VertexConsumer vertexConsumer, float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4, float red, float green, float blue, Direction direction) {
        vertexConsumer.vertex(matrix4f, x1, y1, z1).color(red, green, blue, 1.0F).next();
        vertexConsumer.vertex(matrix4f, x2, y1, z2).color(red, green, blue, 1.0F).next();
        vertexConsumer.vertex(matrix4f, x2, y2, z3).color(red, green, blue, 1.0F).next();
        vertexConsumer.vertex(matrix4f, x1, y2, z4).color(red, green, blue, 1.0F).next();
//        if (direction == endPortalBlockEntity.getOrientation() || direction.getOpposite() == endPortalBlockEntity.getOrientation()) {
//            float offset = direction == endPortalBlockEntity.getOrientation() ? 0.5F : -0.5F;
//            vertexConsumer.vertex(matrix4f, x1, y1, z1 + offset).color(red, green, blue, 1.0F).next();
//            vertexConsumer.vertex(matrix4f, x2, y1, z2 + offset).color(red, green, blue, 1.0F).next();
//            vertexConsumer.vertex(matrix4f, x2, y2, z3 + offset).color(red, green, blue, 1.0F).next();
//            vertexConsumer.vertex(matrix4f, x1, y2, z4 + offset).color(red, green, blue, 1.0F).next();
//        }
    }
}
