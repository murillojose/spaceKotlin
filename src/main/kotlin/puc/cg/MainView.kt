package puc.cg

import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.scene.Group
import javafx.scene.image.ImageView
import tornadofx.*

/**
 * Created by malves on 08/11/2016.
 */

class MainView : View(){
    override val root = Group()
    private val running = true
    private lateinit var Nave :ImageView
    private lateinit var scenario :ImageView
    private var navePos = Point2D(270.0, 500.0)
    private var iniX = 0.0
    private var iniY = 0.0
    var stage = 0

    init {
        with(root) {
            prefWidth(600.0)
            prefWidth(600.0)

            scenario = imageview("Space.jpg") {
                viewport = Rectangle2D(0.0, 0.0, 600.0, 600.0)
            }

            Nave = imageview("Nave.png") {
                x = navePos.x
                y = navePos.y
                viewport = Rectangle2D(0.0, 0.0, 60.0, 60.0)
            }
            setOnMouseClicked { e ->
                if(stage > 3){
                    stage = 0
                }

                if (Nave.x < 540.0) {
                    navePos = navePos.add(20.0, 0.0)
                    Nave.x = navePos.x
                    Nave.y = navePos.y
                    when(stage){
                        0 -> iniX += 60.0
                        1 -> iniX += 54.0
                        2 -> iniX += 55.0
                        3 -> iniX += 61.0
                        else -> {
                            iniX = 0.0
                            stage = 0
                        }
                    }
                    stage++
                } else {
                    navePos = navePos.subtract(540.0, 0.0)
                    Nave.x = navePos.x
                    Nave.y = navePos.y
                }
                if (iniX > 400.0){
                    iniX = 0.0
                }
            }
        }
        val thread = Thread {
            while (running) {
                update()
                Thread.sleep(10)
            }
        }
        thread.start()
    }
    fun update(){
        if(Nave.viewport.maxX < 2400){
            scenario.viewport = Rectangle2D(0.0, 0.0, 600.0, 600.0)
            Nave.viewport = Rectangle2D(iniX, 0.0 , 54.0, 60.0)
        }
    }
 }
