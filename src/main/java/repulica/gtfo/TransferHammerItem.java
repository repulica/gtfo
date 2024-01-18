package repulica.gtfo;

import eu.pb4.factorytools.api.item.AutoModeledPolymerItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.network.packet.s2c.common.ServerTransferS2CPacket;
import net.minecraft.network.packet.s2c.common.StoreCookieS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class TransferHammerItem extends SwordItem implements AutoModeledPolymerItem {
	public TransferHammerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		super.postHit(stack, target, attacker);
		//todo switch to server transfer api when its ready
		if (target instanceof ServerPlayerEntity player) {
			ServerAddress addr = ServerAddress.parse(stack.getName().getLiteralString());
			if (!addr.getAddress().equals("server.invalid")) {
				//todo visual effects
				player.networkHandler.send(new StoreCookieS2CPacket(new Identifier(GTFO.MODID, "hammered"), new byte[]{1}), null);
				player.networkHandler.send(new ServerTransferS2CPacket(addr.getAddress(), addr.getPort()), null);
			}
		}
		return true;
	}

	@Override
	public Item getPolymerItem() {
		return Items.NETHERITE_AXE;
	}
}
