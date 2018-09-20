package nl.knokko.anticaps;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = AntiCapsLock.MODID, name = AntiCapsLock.NAME, version = AntiCapsLock.VERSION)
public class AntiCapsLock
{
    public static final String MODID = "anticapslock";
    public static final String NAME = "Anti Caps Lock";
    public static final String VERSION = "1.8-1";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onClientChat(ClientChatReceivedEvent event){
    	int capsCount = 0;
    	int letterCount = 0;
    	String text = event.message.getUnformattedText();
    	int startIndex = text.indexOf(">");
    	if(startIndex == -1)
    		startIndex = text.indexOf("]");
    	if(startIndex == -1)
    		startIndex = text.indexOf(":");
    	if(startIndex == -1)
    		return;
    	for(int i = startIndex; i < text.length(); i++){
    		if(Character.isUpperCase(text.charAt(i)))
    			capsCount++;
    		if(Character.isLetter(text.charAt(i)))
    			letterCount++;
    	}
    	if(letterCount != 0 && capsCount != 0){
    		double capsPart = capsCount / (double) letterCount;
    		if(capsPart > 0.4)
    			event.message = new ChatComponentTranslation(event.message.getFormattedText().toLowerCase());
    	}
    }
}
