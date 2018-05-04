@GrabConfig(systemClassLoader=true)
@Grab('org.codehaus.groovyfx:groovyfx:0.1')
@Grab('org.xerial:sqlite-jdbc:3.6.16')
import org.sqlite.*
import java.sql.*

import groovyx.javafx.GroovyFX
import groovyx.javafx.SceneGraphBuilder
import java.util.Random
import models.dao.*
import models.entities.*

GroovyFX.start {

    db = groovy.sql.Sql.newInstance("jdbc:sqlite:score.db","org.sqlite.JDBC")
    DbInit.initDb(db)

    def sg = new SceneGraphBuilder()
    def score = 0
    def operation = 'Addition'
    def sign = '+'
    def firstNumber = getRandomInterger()
    def secondNumber = getRandomInterger()
    def feedback = ''
    def operationLable, signLable, firstNumberLable, secondNumberLable, feedbackLable, scoreLable
    def answerTextField

    //create a score for  today
    def currentScore = new Score(score, new java.util.Date())
    def scoreDAO = new ScoreDAO()
    scoreDAO.create(currentScore, db)

  sg.stage(title: "Groovy School Arithmetic Game", visible: true) {
    scene(width: 400, height: 500) {
        vbox(spacing: 10){
            menuBar(){
                menu('Operation'){
                    menuItem("Addition", onAction:{ 
                        operationLable.setText("Addition")
                        operation = 'Addition'
                        sign = "+"
                        signLable.setText("+")
                        firstNumber = getRandomInterger()
                        firstNumberLable.setText(firstNumber+"")
                        secondNumber = getRandomInterger()
                        secondNumberLable.setText(secondNumber+"")
                    })
                    menuItem("Subtraction", onAction:{ 
                        operationLable.setText("Subtraction")
                        operation = 'Subtraction'
                        sign = "-"
                        signLable.setText("-")
                        firstNumber = getRandomInterger()
                        firstNumberLable.setText(firstNumber.toString())
                        secondNumber = getRandomInterger()
                        secondNumberLable.setText(secondNumber.toString())
                    })
                    menuItem("Multiplication", onAction:{ 
                        opesrationLable.setText("Multiplication")
                        operation = 'Multiplication'
                        sign = "X"
                        signLable.setText("X")
                        firstNumber = getRandomInterger()
                        firstNumberLable.setText(firstNumber.toString())
                        secondNumber = getRandomInterger()
                        secondNumberLable.setText(secondNumber.toString())
                    })
                    menuItem("Division", onAction:{ 
                        operationLable.setText("Division")
                        operation = 'Division'
                        sign = "/"
                        signLable.setText("/")
                        firstNumber = getRandomInterger()
                        firstNumberLable.setText(firstNumber.toString())
                        secondNumber = getRandomInterger()
                        secondNumberLable.setText(secondNumber.toString())
                    })
                }
            }
            hbox(spacing: 10, padding:[5,25, 0, 0], alignment: "center_right"){
                label("Score: ", textFill: black, style: "-fx-font-size: 20pt;")
                scoreLable = label(score, textFill: black, style: "-fx-font-size: 15pt;")
            }
             vbox(spacing: 10, alignment:'center'){
                operationLable = label(operation, textFill: black, style: "-fx-font-size: 20pt;")
                firstNumberLable = label(firstNumber, textFill: black, style: "-fx-font-size: 30pt;")
                signLable = label(sign, textFill: black, style: "-fx-font-size: 30pt;")
                secondNumberLable = label(secondNumber,textFill: black, style: "-fx-font-size: 30pt;")
                hbox(spacing: 10, alignment:'center'){
                    label('= ', textFill: black, style: "-fx-font-size: 20pt;")
                    answerTextField = textField(promptText: 'Answer')
                }
                button('Submit', style: "-fx-font-size: 15pt", onAction:{
                    if(answerTextField.getText() == null || answerTextField.getText() == ""){
                        feedbackLable.setText("Invalid input")
                    }else{
                        def answer = Double.parseDouble(answerTextField.getText()) 
                        if(operation == "Addition"){
                            if(answer == firstNumber + secondNumber){
                                score += 10
                                scoreLable.setText(score+"")
                                feedbackLable.setText("Very Good")
                                firstNumber = getRandomInterger()
                                firstNumberLable.setText(firstNumber+"")
                                secondNumber = getRandomInterger()
                                secondNumberLable.setText(secondNumber+"")
                                answerTextField.setText("")
                            } else {
                                score -= 5
                                scoreLable.setText(score+"")
                                feedbackLable.setText("Try Again")
                            }
                        } else if(operation == "Subtraction"){
                            if(answer == firstNumber - secondNumber){
                                score += 10
                                scoreLable.setText(score+"")
                                feedbackLable.setText("Very Good")
                                firstNumber = getRandomInterger()
                                firstNumberLable.setText(firstNumber+"")
                                secondNumber = getRandomInterger()
                                secondNumberLable.setText(secondNumber+"")
                                answerTextField.setText("")
                            } else {
                                score -= 5
                                scoreLable.setText(score+"")
                                feedbackLable.setText("Try Again")
                            }
                        } else if(operation == "Multiplication"){
                            if(answer == firstNumber * secondNumber){
                                score += 10
                                scoreLable.setText(score+"")
                                feedbackLable.setText("Very Good")
                                firstNumber = getRandomInterger()
                                firstNumberLable.setText(firstNumber+"")
                                secondNumber = getRandomInterger()
                                secondNumberLable.setText(secondNumber+"")
                                answerTextField.setText("")
                            } else {
                                score -= 5
                                scoreLable.setText(score+"")
                                feedbackLable.setText("Try Again")
                            }
                        } else if(operation == "Division"){
                            def correctAnswer = firstNumber / secondNumber
                            if(answer == (Math.round(correctAnswer * 10) / 10)){
                                score += 10
                                scoreLable.setText(score+"")
                                feedbackLable.setText("Very Good")
                                firstNumber = getRandomInterger()
                                firstNumberLable.setText(firstNumber+"")
                                secondNumber = getRandomInterger()
                                secondNumberLable.setText(secondNumber+"")
                                answerTextField.setText("")
                            } else {
                                score -= 5
                                scoreLable.setText(score+"")
                                feedbackLable.setText("Try Again")
                            }
                        } else{
                            feedbackLable.setText("Something went wrong!")
                        }

                        currentScore = new Score(score, new java.util.Date())
                        scoreDAO.update(currentScore, db)
                    }
                })
                feedbackLable = label(text: feedback)
            }
        }
      }
    } 

  }

    def add (firstNumber, secondNumber){
        firstNumber + secondNumber
    }

    def subtract (firstNumber, secondNumber){
        firstNumber - secondNumber
    }

    def multiply (firstNumber, secondNumber){
        firstNumber * secondNumber
    }

    def divide (firstNumber, secondNumber){
        firstNumber / secondNumber
    }

    def getRandomInterger(){
       Math.abs(new Random().nextInt()%10) + 1
    }

    def refresh(){
        firstNumber = getRandomInterger()
        secondNumber = getRandomInterger()
    }

    def doSubtraction(){
        ArithmeticGame.operation = "Subtration"
        refresh()
    }

     def doMultiplication(){
        ArithmeticGame.operation = "Multiplication"
        refresh()
    }

    def doDivision(){
        ArithmeticGame.operation = "Division"
        refresh()
    }