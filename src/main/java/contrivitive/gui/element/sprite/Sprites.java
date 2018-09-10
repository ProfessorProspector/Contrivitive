package contrivitive.gui.element.sprite;

import contrivitive.lib.ContrivitiveConstants;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class Sprites {

	public static final HashMap<String, Sprite> SPRITES = new HashMap<>();
	public static final HashMap<String, ResourceLocation> TEXTURE_SHEETS = new HashMap<>();

	public static final ResourceLocation BACKGROUND = register("contrivitive:background", new ResourceLocation(ContrivitiveConstants.MOD_ID, "textures/gui/background.png"));
	public static final ResourceLocation SHEET = register("contrivitive:sheet", new ResourceLocation(ContrivitiveConstants.MOD_ID, "textures/gui/sheet.png"));

	public static final Sprite SLOT_NORMAL = register("contrivitive:slot_normal", new SheetSprite(SHEET, 0, 0, 18, 18));
	public static final Sprite SLOT_NORMAL_BUTTON = register("contrivitive:slot_button", new SheetSprite(SHEET, 18, 0, 18, 18));
	public static final Sprite SETTINGS_ICON = register("contrivitive:settings_icon", new SheetSprite(SHEET, 37, 1, 16, 16));
	public static final Sprite JEI_ICON = register("contrivitive:jei_icon", new SheetSprite(SHEET, 55, 1, 16, 16));
	public static final Sprite BURN_BAR_EMPTY = register("contrivitive:burn_bar_empty", new SheetSprite(SHEET, 74, 2, 14, 14));
	public static final Sprite BURN_BAR_FULL = register("contrivitive:burn_bar_full", new SheetSprite(SHEET, 92, 2, 14, 14));
	public static final Sprite X_ICON_ODD = register("contrivitive:x_icon_odd", new SheetSprite(SHEET, 109, 1, 15, 15));
	public static final Sprite X_ICON_EVEN = register("contrivitive:x_icon_even", new SheetSprite(SHEET, 127, 1, 16, 16));

	public static Sprite register(String identifier, Sprite sprite) {
		SPRITES.put(identifier, sprite);
		return sprite;
	}

	public static ResourceLocation register(String identifier, ResourceLocation textureSheet) {
		TEXTURE_SHEETS.put(identifier, textureSheet);
		return textureSheet;
	}
}
