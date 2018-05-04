package models.entities

public class Score {
    
    private def id
    private def points
    private def date

    Score(id, points, date) {
        this.id = id
        this.points = points
        this.date = date
    }

    Score(points, date) {
        this.points = points
        this.date = date
    }

    public Score (){ }

    public def getId(){
        this.id
    }

    public setId(id){
        this.id = id
    }

    public def getPoints(){
        this.id
    }

    public setPoints(points){
        this.points = points
    }

    public String toString(){
        this.points.toString()
    }

    public def getDate() {
        this.date

    }

    public void setDate(date) {
        this.date = date
    }
}
