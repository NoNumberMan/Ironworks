package com.ironworks.core;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WireHookBlock extends BlockWithEntity {
	public static final DirectionProperty FACING = DirectionProperty.of( "Direction" );
	private final int maxTransfer;

	public WireHookBlock( Settings settings, final int maxTransfer ) {
		super( settings );
		//this.setDefaultState(( BlockState )((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)));
		this.maxTransfer = maxTransfer;
	}

	public int getMaxTransfer() {
		return maxTransfer;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity( BlockPos pos, BlockState state ) {
		return new WireHookBlockEntity( pos, state );
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker( World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, IronworksCore.WIRE_HOOK_BLOCK_ENTITY_TYPE,
				(world1, pos, state1, be) -> WireHookBlockEntity.tick(world1, pos, state1, (WireHookBlockEntity)be));
	}
}
