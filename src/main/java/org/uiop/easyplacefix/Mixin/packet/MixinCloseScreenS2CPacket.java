package org.uiop.easyplacefix.Mixin.packet;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static org.uiop.easyplacefix.EasyPlaceFix.crafterOperation;

@Mixin(CloseScreenS2CPacket.class)
public class MixinCloseScreenS2CPacket {
    @WrapWithCondition(
            method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/network/listener/ClientPlayPacketListener;" +
                            "onCloseScreen(Lnet/minecraft/network/packet/s2c/play/CloseScreenS2CPacket;)V"
            ))
    private boolean closeScreenFail(ClientPlayPacketListener instance, CloseScreenS2CPacket closeScreenS2CPacket) {
        if (crafterOperation) {
            crafterOperation = false;
        }
        return true;
    }
}
