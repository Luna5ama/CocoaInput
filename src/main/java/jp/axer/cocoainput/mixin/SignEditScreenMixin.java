package jp.axer.cocoainput.mixin;

import jp.axer.cocoainput.wrapper.SignEditScreenWrapper;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.client.gui.screens.inventory.SignEditScreen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSignEditScreen.class)
public class SignEditScreenMixin {
    SignEditScreenWrapper wrapper;

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        wrapper = new SignEditScreenWrapper((SignEditScreen) (Object) this);
    }

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractSignEditScreen;frame:I", opcode = Opcodes.PUTFIELD))
    private void injectCurosr(AbstractSignEditScreen instance, int value) {
        instance.frame = wrapper.renewCursorCounter();
    }
}
