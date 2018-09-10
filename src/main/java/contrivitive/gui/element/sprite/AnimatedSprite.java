package contrivitive.gui.element.sprite;

import contrivitive.gui.GuiBlueprint;
import contrivitive.gui.IContrivitiveGui;
import contrivitive.gui.element.Coordinate;
import contrivitive.lib.ContrivitiveConstants;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = ContrivitiveConstants.MOD_ID)
public class AnimatedSprite extends Sprite {
	public final int animationTime;
	public final List<Pair<Sprite, Coordinate>> expandedFrames;
	public float counter = 0;

	public AnimatedSprite(int width, int height, int offsetX, int offsetY, List<AnimationFrame> frames) {
		super(width, height, offsetX, offsetY);
		int animationTime = 0;
		List<Pair<Sprite, Coordinate>> expandedFrames = new ArrayList<>();
		for (AnimationFrame frame : frames) {
			animationTime += frame.lifetime;
			for (int i = 0; i < frame.lifetime; i++) {
				expandedFrames.add(Pair.of(frame.sprite, frame.relativePos));
			}
		}
		this.animationTime = animationTime;
		this.expandedFrames = expandedFrames;
	}

	public AnimatedSprite(int width, int height, List<AnimationFrame> frames) {
		this(width, height, 0, 0, frames);
	}

	public AnimatedSprite(int width, int height, int offsetX, int offsetY, AnimationFrame... frames) {
		this(width, height, offsetX, offsetY, Arrays.asList(frames));
	}

	public AnimatedSprite(int width, int height, AnimationFrame... frames) {
		this(width, height, 0, 0, frames);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void drawScreenEvent(GuiScreenEvent.DrawScreenEvent event) {
		if (event.getGui() instanceof IContrivitiveGui && !((IContrivitiveGui<?,?>) event.getGui()).disableAnimations()) {
		}
	}

	public Pair<Sprite, Coordinate> getFrame() {
		int cycleTime = (int) Math.floor(counter) % animationTime;
		return expandedFrames.get(cycleTime);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public <G extends GuiScreen, B extends GuiBlueprint> void draw(IContrivitiveGui<G,B> gui, int x, int y, float elapsedTicks) {
		counter += elapsedTicks;
		Pair<Sprite, Coordinate> frame = getFrame();
		frame.getLeft().draw(gui, x + offsetX + frame.getRight().x, y + offsetY + frame.getRight().y, elapsedTicks);
	}

}
