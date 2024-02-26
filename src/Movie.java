public class Movie {
    private String name;
    private String cast;
    private String director;
    private String overview;
    private int runtime;
    private double userRating;
    public Movie(String name,String cast, String director, String overview, int runtime, double userRating){
        this.name = name;
        this.cast = cast;
        this.director = director;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }
    public String getName(){
        return name;
    }
    public String getCast(){
        return cast;
    }
    public void  setName(String str){
        name = str;
    }



    public String getDirector() {
        return director;
    }




    public String getOverview() {
        return overview;
    }




    public int getRuntime() {
        return runtime;
    }




    public double getUserRating() {
        return userRating;
    }
    public String toString() {
        return name;
    }
    public String dis(){
        return "Title: "+name+"\nRuntime: "+runtime+" minutes"+"\nDirected by: "+director+"\nCast: "+cast+"\nOverview: "+overview+"\nUser rating: "+userRating;
    }

}
