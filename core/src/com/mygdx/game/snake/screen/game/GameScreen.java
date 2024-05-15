package com.mygdx.game.snake.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jga.snake.system.DirectionSystem;
import com.mygdx.game.snake.SnakeGame;
import com.mygdx.game.snake.assets.AssetDescriptors;
import com.mygdx.game.snake.common.GameManager;
import com.mygdx.game.snake.config.GameConfig;
import com.mygdx.game.snake.screen.menu.MenuScreen;
import com.mygdx.game.snake.system.*;
import com.mygdx.game.snake.system.debug.DebugCameraSystem;
import com.mygdx.game.snake.system.debug.DebugInputSystem;
import com.mygdx.game.snake.system.debug.DebugRenderSystem;
import com.mygdx.game.snake.system.debug.GridRenderSystem;
import com.mygdx.game.snake.system.passive.EntityFactorySystem;
import com.mygdx.game.snake.system.passive.SnakeSystem;
import com.mygdx.game.snake.system.passive.SoundSystem;
import com.mygdx.game.snake.system.passive.StartUpSystem;
import com.mygdx.game.snake.util.GdxUtils;


public class GameScreen extends ScreenAdapter {

    // == constants ==
    private static final Logger log = new Logger(GameScreen.class.getSimpleName(), Logger.DEBUG);

    // == attributes ==
    private final SnakeGame game;
    private final AssetManager assetManager;
    private final SpriteBatch batch;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private BitmapFont font;

    // == constructors ==
    public GameScreen(SnakeGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
        batch = game.getBatch();
    }

    // == public methods ==
    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        font = assetManager.get(AssetDescriptors.UI_FONT);

        engine.addSystem(new EntityFactorySystem(assetManager));
        engine.addSystem(new SoundSystem(assetManager));
        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugCameraSystem(
                GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y,
                camera
        ));

        engine.addSystem(new DebugRenderSystem(viewport, renderer));
        engine.addSystem(new DebugInputSystem());
        engine.addSystem(new SnakeSystem());
        engine.addSystem(new DirectionSystem());
        engine.addSystem(new SnakeMovementSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CoinSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new RenderSystem(batch, viewport));
        engine.addSystem(new HudRenderSystem(batch, hudViewport, font));
        engine.addSystem(new StartUpSystem());

        GameManager.INSTANCE.reset();
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        engine.update(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        engine.removeAllEntities();
    }
}
