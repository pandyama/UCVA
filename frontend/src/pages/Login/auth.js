class Auth{
    constructor(){
        if(localStorage.getItem("authenticated") === null){
            this.authenticated =  0;
        }
        else{
            this.authenticated =  localStorage.getItem("authenticated");
        }
    }

    login(cb){
        cb();
    }
    isAuthenticated(){
        return this.authenticated;
    }
    setAuthenticated(value){
        this.authenticated = value;
        localStorage.setItem("authenticated",value);
    }
    isAuthorized(){
        return localStorage.getItem("accessLevel");
    }
}

export default new Auth();