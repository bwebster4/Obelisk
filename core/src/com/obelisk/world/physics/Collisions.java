package com.obelisk.world.physics;

<<<<<<< HEAD
=======
import com.badlogic.gdx.math.Rectangle;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.InputHandler;
import com.obelisk.world.Chunk;
import com.obelisk.world.Map;
import com.obelisk.world.WorldMain;
<<<<<<< HEAD
import com.obelisk.world.mapelems.Block;
import com.obelisk.world.entities.ActiveEntity;
=======
import com.obelisk.world.blocks.Block;
import com.obelisk.world.entities.ActiveEntity;
import com.obelisk.world.entities.EntityManager;
import com.obelisk.world.entities.Projectile;
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a

public class Collisions {
	Array<Chunk> chunkarray;
	Array<Block> blockarray;
	Block block;
	Block testblock1, testblock2, testblock3, testblock4;
	Chunk chunk, chunk1, chunk2, chunk3;
	InputHandler input;
	Vector3 mousepos;
	Map map;
	
	public Collisions(Array<Chunk> chunkarray, InputHandler input, Map map){
		this.chunkarray = chunkarray;
		this.map = map;
		this.input = input;
	}
	
	public void updateChunkArray(Array<Chunk> chunkarray){
		this.chunkarray = chunkarray;
	}
	public void mouseTouch(ActiveEntity entity){
		mousepos = WorldMain.touchpos;
		for (int i = 0; i < chunkarray.size; i++){
			chunk = chunkarray.get(i);
			blockarray = chunk.getBlockArray();
//			for (int j = 0; j < blockarray.size; j++){
//				block = blockarray.get(j);
//				Rectangle blockrect = new Rectangle(block.pos.x, block.pos.y, block.width, block.height);
//				if (blockrect.contains(mousepos.x, mousepos.y)){
//					entity.mine(block);
//				}
//			}
		}
	}
	
//	public void checkCollisions(EntityManager entitymanager, ActiveEntity entity){
//		
////		if (!map.getWalkable(entity.pos.x, entity.pos.y)){
////			testblock1 = map.getBlock(entity.pos.x, entity.pos.y);
////			entity.blockCollision(testblock1);
////			testblock1.entityCollision(entity);
////		}else if(!map.getWalkable(entity.pos.x + entity.width, entity.pos.y)){
////			testblock1 = map.getBlock(entity.pos.x + entity.width, entity.pos.y);
////			entity.blockCollision(testblock1);
////			testblock1.entityCollision(entity);
////		}else if(!map.getWalkable(entity.pos.x, entity.pos.y + entity.height)){
////			testblock1 = map.getBlock(entity.pos.x, entity.pos.y + entity.height);
////			entity.blockCollision(testblock1);
////			testblock1.entityCollision(entity);
////		}else if(!map.getWalkable(entity.pos.x + entity.width, entity.pos.y + entity.height)){
////			testblock1 = map.getBlock(entity.pos.x + entity.width, entity.pos.y + entity.height);
////			entity.blockCollision(testblock1);
////			testblock1.entityCollision(entity);
////		}
//		Rectangle rect1 = entity.getSprite().getBoundingRectangle();
//		
//		chunk1 = map.getChunk(entity.getPos().x, entity.getPos().y);
//		if (chunk1.atEdgeX(entity.getPos().x - chunk1.getX() * Map.CHUNK_SIZE) == 0)
//			chunk2 = map.getChunk(entity.getPos().x - 1, entity.getPos().y);
//		else if (chunk1.atEdgeX(entity.getPos().x - chunk1.getX() * Map.CHUNK_SIZE) == 1)
//			chunk2 = map.getChunk(entity.getPos().x + 1, entity.getPos().y);
//		if (chunk1.atEdgeY(entity.getPos().y - chunk1.getY() * Map.CHUNK_SIZE) == 0)
//			chunk3 = map.getChunk(entity.getPos().x, entity.getPos().y - 1);
//		else if (chunk1.atEdgeX(entity.getPos().y - chunk1.getY() * Map.CHUNK_SIZE) == 1)
//			chunk3 = map.getChunk(entity.getPos().x, entity.getPos().y + 1);
//		
//		for (int i = 0; i < chunk1.getBlockArray().size; i++){
//			block = chunk1.getBlockArray().get(i);
//			Rectangle rect2 = new Rectangle(block.pos.x, block.pos.y, block.width, block.height);
//			if (rect1.overlaps(rect2)){
//				entity.blockCollision(block);
//				block.entityCollision(entity);
//			}
//		}
//		if (chunk2 != null){
//			for (int i = 0; i < chunk2.getBlockArray().size; i++){
//				block = chunk2.getBlockArray().get(i);
//				Rectangle rect2 = new Rectangle(block.pos.x, block.pos.y, block.width, block.height);
//				if (rect1.overlaps(rect2)){
//					entity.blockCollision(block);
//					block.entityCollision(entity);
//				}
//			}
//		}
//		if (chunk3 != null){
//			for (int i = 0; i < chunk3.getBlockArray().size; i++){
//				block = chunk3.getBlockArray().get(i);
//				Rectangle rect2 = new Rectangle(block.pos.x, block.pos.y, block.width, block.height);
//				if (rect1.overlaps(rect2)){
//					entity.blockCollision(block);
//					block.entityCollision(entity);
//				}
//			}
//		}
//		
//		if (entity.getClass() != Projectile.class){
//			for (int i = 0; i < WorldMain.entitymanager.projectiles.size; i++){
//				Projectile projectile = WorldMain.entitymanager.projectiles.get(i);
//				Rectangle rect2 = projectile.getSprite().getBoundingRectangle();
//				if (rect1.overlaps(rect2) && projectile.getFaction() != entity.getFaction()){
//					entity.projectileCollision(projectile);
//					projectile.blockCollision(null);
//				}
//			}
//			for (int i = 0; i < WorldMain.entitymanager.loadedentities.size; i++){
//				ActiveEntity entity2 = WorldMain.entitymanager.loadedentities.get(i);
//				if (!entity.equals(entity2)){
//					Rectangle rect2 = entity2.getSprite().getBoundingRectangle();
//					if (rect1.overlaps(rect2)){
//						entity.entityCollision(entity2);
//					}
//				}
//			}
//		}
//	}
}
