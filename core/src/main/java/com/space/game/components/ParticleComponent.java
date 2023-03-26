package com.space.game.components;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.space.game.components.external.Component;

import java.util.ArrayList;
import java.util.List;

public class ParticleComponent implements Component {


    private List<ParticleEffect> particleEffectList = new ArrayList<>();

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean isEnabled(final boolean enable) {
        return true;
    }

    public void addEffect(FileHandle particle, FileHandle particleTexture) {
        ParticleEffect pe = new ParticleEffect();
        pe.load(particle, particleTexture);
        particleEffectList.add(pe);
    }

    public List<ParticleEffect> getParticleEffectList() {
        return particleEffectList;
    }

    public void clearParticleEffect() {
        for (ParticleEffect pe : particleEffectList) {
            if (pe.isComplete()) {
                particleEffectList.remove(pe);
            }
        }
    }

}
