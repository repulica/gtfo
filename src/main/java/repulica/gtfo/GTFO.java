package repulica.gtfo;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class GTFO implements ModInitializer {
	public static final String MODID = "gtfo";

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier(MODID, "transfer_hammer"), new TransferHammerItem(ToolMaterials.NETHERITE, 5, 3.2f, new FabricItemSettings().rarity(Rarity.EPIC)));
		PolymerResourcePackUtils.addModAssets(MODID);
	}
}
