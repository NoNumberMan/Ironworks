package com.ironworks.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class WireHookBlockEntity extends BlockEntity {
	private IEnergyReceiver attachedReceiver = null;
	private IEnergyProvider attachedProvider = null;
	private WireHookBlockEntity connected = null;

	public WireHookBlockEntity( BlockPos pos, BlockState state ) {
		super( IronworksCore.WIRE_HOOK_BLOCK_ENTITY_TYPE, pos, state );
	}

	public static void tick(World world, BlockPos pos, BlockState state, WireHookBlockEntity wireHookBlockEntity) {
		if ( wireHookBlockEntity.connected == null ) return;

		//pull to this hook
		if ( wireHookBlockEntity.attachedReceiver != null && wireHookBlockEntity.connected.attachedProvider != null ) {
			if ( !wireHookBlockEntity.attachedReceiver.isFull() && !wireHookBlockEntity.connected.attachedProvider.isEmpty() ) {
				final int maxTransfer = ((WireHookBlock) (state.getBlock())).getMaxTransfer();
				final int pushable = Math.min( maxTransfer, wireHookBlockEntity.attachedReceiver.getCapacity()
						- wireHookBlockEntity.attachedReceiver.getCurrentCharge());
				final int toPush = wireHookBlockEntity.connected.attachedProvider.pullEnergy( pushable );
				final int pushed = wireHookBlockEntity.attachedReceiver.pushEnergy( toPush );
				assert(pushed == 0);
			}
		}
	}

	public void updateAttached( World world, BlockPos pos, BlockState state ) {
		Direction facing = state.get( WireHookBlock.FACING );
		BlockPos connectedPos = pos.offset( facing.getOpposite() );
		BlockEntity blockEntity = world.getBlockEntity( connectedPos );
		if ( blockEntity != null ) {
			if ( blockEntity instanceof IEnergyReceiver ) attachedReceiver = (IEnergyReceiver) blockEntity;
			else attachedReceiver = null;

			if ( blockEntity instanceof IEnergyProvider ) attachedProvider= (IEnergyProvider) blockEntity;
			else attachedProvider = null;
		}
	}

	public void connect( WireHookBlockEntity other ) {
		this.connected = other;
	}

	public void disconnect() {
		this.connected = null;
	}
}
