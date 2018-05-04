package models.dao

import models.entities.*

public class ScoreDAO {

    public create(score, db){
        String query = "INSERT INTO scores(points, date) VALUES (" +
                score.getPoints() + "," +
                score.getDate().getTime() + ")"
        db.execute(query)
    }

    public def readAll(db){
        String query = "SELECT * FROM scores"
        List scores = new ArrayList<>()
        db.eachRow(query) {
            def score = new Score()
            score.setId(it.id)
            score.setPoints(it.points)
            score.setDate(it.date)
            scores.add(score)
        }
        return scores
    }

    public def readById(id ,db){
        String query = "SELECT * FROM scores WHERE id = " + id
        def score = new Score()
        db.eachRow(query) {
            score.setId(it.id)
            score.setPoints(it.points)
            score.setDate(it.date)
        }
        return score
    }

    public update(score, db){
        String query = "UPDATE scores SET" +
                " points = " + score.getPoints() + " ," +
                " date = " + score.getDate().getTime() +
                " WHERE id = " + score.getId()
        db.execute(query)
    }

    public delete(id, db){
        String query = "DELETE FROM scores WHERE id =" + id
        db.execute(query)
    }
    
}