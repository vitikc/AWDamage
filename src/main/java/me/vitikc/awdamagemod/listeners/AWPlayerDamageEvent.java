package me.vitikc.awdamagemod.listeners;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import me.vitikc.awdamagemod.config.AWValuesManager;
import me.vitikc.awdamagemod.draw.AWEnumMessages;
import me.vitikc.awdamagemod.packet.AWMessage;
import me.vitikc.awdamagemod.packet.AWPacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class AWPlayerDamageEvent {
    @SubscribeEvent
    public void onPlayerTakeDamage(LivingHurtEvent event){
        if (!(event.entity instanceof EntityPlayer)){
            return;
        }
        DamageSource damageSource = event.source;

        Entity damager = null;

        // Check on which side event is called
        // For forge client - getEntity
        // For cauldron server - reflected method
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
            damager = getDamageSourceCauldron(damageSource);
        } else {
            damager = event.source.getEntity();
        }
        if (damager == null) return;
        if (!(damager instanceof EntityPlayerMP)){
            return;
        }
        EntityPlayerMP player = (EntityPlayerMP) event.entity;
        AWMessage msg;
        if (isChance(AWValuesManager.EVADE_CHANCE)){
            event.setCanceled(true);
            msg = new AWMessage(AWEnumMessages.EVADED.id);
            AWPacketHandler.INSTANCE.sendTo(msg, player);
            return;
        }
        if (isChance(AWValuesManager.PARRY_CHANCE)){
            event.ammount *= AWValuesManager.PARRY_PERCENT;
            msg = new AWMessage(AWEnumMessages.PARRIED.id);
            AWPacketHandler.INSTANCE.sendTo(msg, player);
        }
    }
    @SubscribeEvent
    public void onPlayerGiveDamage(LivingHurtEvent event){
        if (!(event.entity instanceof EntityPlayer)){
            return;
        }
        DamageSource damageSource = event.source;
        Entity entity = null;
        // Check on which side event is called
        // For forge client - getEntity
        // For cauldron server - reflected method
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
            entity = getDamageSourceCauldron(damageSource);
        } else {
            entity = event.source.getEntity();
        }
        if (entity == null) return;
        if (!(entity instanceof EntityPlayerMP)){
            return;
        }
        EntityPlayerMP player = (EntityPlayerMP) entity;
        if (isChance(AWValuesManager.CRITICAL_CHANCE)){
            event.ammount *= AWValuesManager.CRITICAL_PERCENT;
            AWMessage msg = new AWMessage(AWEnumMessages.CRITICAL.id);
            AWPacketHandler.INSTANCE.sendTo(msg, player);
        }
    }


    // Simple chance generator
    public boolean isChance(int percent){
        Random random = new Random();
        int rnum = random.nextInt(100);
        return rnum <= percent;
    }
    // Cauldron server don't have method DamageSource.getEntity()
    // But there is method which returns DamageSource.entity
    // Using reflection for getting this method
    public Entity getDamageSourceCauldron(DamageSource damageSource){
        Method method = null;
        Entity object = null;
        Class cls = damageSource.getClass();
        try {
            method = cls.getMethod("func_76346_g");
            object = (Entity) method.invoke(damageSource);
            /*
            System.out.println(method.getName());
            System.out.println(method.getReturnType());
            System.out.println(method.toString());
            System.out.println(method.getDefaultValue());*/

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (object ==null) {
            return null;
        }
        return object;
    }
}
