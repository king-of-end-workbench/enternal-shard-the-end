/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.end_elemetn.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.end_elemetn.client.model.*;

@EventBusSubscriber(Dist.CLIENT)
public class EndElemetnModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modellaser.LAYER_LOCATION, Modellaser::createBodyLayer);
		event.registerLayerDefinition(Modelblastling_control.LAYER_LOCATION, Modelblastling_control::createBodyLayer);
		event.registerLayerDefinition(Modelthumpling.LAYER_LOCATION, Modelthumpling::createBodyLayer);
		event.registerLayerDefinition(Modelnecrosent.LAYER_LOCATION, Modelnecrosent::createBodyLayer);
		event.registerLayerDefinition(Modelsnareling_control.LAYER_LOCATION, Modelsnareling_control::createBodyLayer);
		event.registerLayerDefinition(Modellaser_beam.LAYER_LOCATION, Modellaser_beam::createBodyLayer);
		event.registerLayerDefinition(Modelcuttedreality.LAYER_LOCATION, Modelcuttedreality::createBodyLayer);
		event.registerLayerDefinition(ModelCelestian_Guard.LAYER_LOCATION, ModelCelestian_Guard::createBodyLayer);
		event.registerLayerDefinition(Modelerr_entity_shadow.LAYER_LOCATION, Modelerr_entity_shadow::createBodyLayer);
		event.registerLayerDefinition(ModelJELLY.LAYER_LOCATION, ModelJELLY::createBodyLayer);
		event.registerLayerDefinition(Modelwatchling.LAYER_LOCATION, Modelwatchling::createBodyLayer);
		event.registerLayerDefinition(Modelbullet.LAYER_LOCATION, Modelbullet::createBodyLayer);
		event.registerLayerDefinition(Modelenderom.LAYER_LOCATION, Modelenderom::createBodyLayer);
		event.registerLayerDefinition(Modelsnareling.LAYER_LOCATION, Modelsnareling::createBodyLayer);
		event.registerLayerDefinition(Modelbaby_JELLY.LAYER_LOCATION, Modelbaby_JELLY::createBodyLayer);
		event.registerLayerDefinition(Modelendersentt.LAYER_LOCATION, Modelendersentt::createBodyLayer);
		event.registerLayerDefinition(ModelENDJ.LAYER_LOCATION, ModelENDJ::createBodyLayer);
		event.registerLayerDefinition(Modelblastling_bullet.LAYER_LOCATION, Modelblastling_bullet::createBodyLayer);
		event.registerLayerDefinition(Modelblastling.LAYER_LOCATION, Modelblastling::createBodyLayer);
		event.registerLayerDefinition(Modelendersent.LAYER_LOCATION, Modelendersent::createBodyLayer);
		event.registerLayerDefinition(Modellureling.LAYER_LOCATION, Modellureling::createBodyLayer);
		event.registerLayerDefinition(Modelwatchling_control.LAYER_LOCATION, Modelwatchling_control::createBodyLayer);
		event.registerLayerDefinition(Modelbabyender.LAYER_LOCATION, Modelbabyender::createBodyLayer);
		event.registerLayerDefinition(Modelsnareling_blob.LAYER_LOCATION, Modelsnareling_blob::createBodyLayer);
		event.registerLayerDefinition(Modelvengeful_heart.LAYER_LOCATION, Modelvengeful_heart::createBodyLayer);
	}
}