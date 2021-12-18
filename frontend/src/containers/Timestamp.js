class Timestamp{
    constructor(){
        this.time = Date().toLocaleString().split(" ")[1]
        +"-"+Date().toLocaleString().split(" ")[2]+"-"+
        Date().toLocaleString().split(" ")[3]+" "+
        Date().toLocaleString().split(" ")[4];
    }
    getCurrentTime(){
        return Date().toLocaleString().split(" ")[1]
        +"-"+Date().toLocaleString().split(" ")[2]+"-"+
        Date().toLocaleString().split(" ")[3]+" "+
        Date().toLocaleString().split(" ")[4];
    }
}

export default new Timestamp();