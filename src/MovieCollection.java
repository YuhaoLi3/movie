import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.IOException;
public class MovieCollection {
    private ArrayList<Movie> movieList;
    private Scanner scanner;
    public MovieCollection(){
        movieList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public void start(){
        readData();
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";


        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();


            if (menuOption.equals("t")) {
                System.out.println(searchTitles());
            } else if (menuOption.equals("c")) {
                System.out.println(searchCast());
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }




    }
    private void readData() {
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String name = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overview = splitData[3];
                int runtime = Integer.parseInt(splitData[4]);
                double userRating = Double.parseDouble(splitData[5]);
                Movie item = new Movie(name, cast,director,overview,runtime, userRating);
                movieList.add(item);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public String searchTitles(){
        System.out.println("Enter a title search term: ");
        String searchTerm = scanner.nextLine().toLowerCase(Locale.ROOT);

        ArrayList<Movie> matchingMovies = new ArrayList<>();

        for (Movie movie : movieList) {
            String movieName = movie.getName().toLowerCase(Locale.ROOT);

            if (movieName.contains(searchTerm)) {
                matchingMovies.add(movie);
            }
        }
        if(matchingMovies.size()==0){
            return "No movie titles match that search term!";
        }
        for (int i = 0; i < matchingMovies.size(); i++) {
            for (int j = i + 1; j < matchingMovies.size(); j++) {
                if (matchingMovies.get(i).getName().compareToIgnoreCase(matchingMovies.get(j).getName()) > 0) {
                    // Swap the entire Movie objects
                    Movie temp = matchingMovies.get(i);
                    matchingMovies.set(i, matchingMovies.get(j));
                    matchingMovies.set(j, temp);
                }
            }
        }

        String str = "";
        int count  =1;
        for(int i =0; i<matchingMovies.size();i++){
            str+= count+ ". "+matchingMovies.get(i)+"\n";
            count ++;
        }
        System.out.println(str);
        System.out.println("Which movie would you like to learn more about?");
        System.out.println("Enter number: ");
        int answer = scanner.nextInt();
        scanner.nextLine();
        return matchingMovies.get(answer-1).dis();
    }
    public String searchCast() {
        ArrayList<String> casts = new ArrayList<>();
        for(int i =0;i<movieList.size();i++){
            String[] x = movieList.get(i).getCast().split("\\|");;
            for(int j=0;j<x.length;j++){
                if(!casts.contains(x[j])){
                    casts.add(x[j]);
                }
            }
        }
        for(int i =0;i< casts.size();i++){
            for(int j=i+1;j< casts.size();j++){
                if(casts.get(i).compareToIgnoreCase(casts.get(j))>0){
                    String temp = casts.get(i);
                    casts.set(i,casts.get(j));
                    casts.set(j,temp);
                }
            }
        }
        System.out.println("enter a person to search for(first or last name): ");
        String word = scanner.nextLine().toLowerCase(Locale.ROOT);
        ArrayList<String> newCasts = new ArrayList<>();

        for (String cast : casts) {
            String[] castNames = cast.split(" ");
            for (String name : castNames) {
                if (name.toLowerCase(Locale.ROOT).contains(word)) {
                    newCasts.add(cast);
                    break;
                }
            }
        }

        if(newCasts.size()==0){
            return "No results match your search";
        }
        String str = "";
        int count  =1;
        for(int i =0; i<newCasts.size();i++){
            str+= count+ ". "+newCasts.get(i)+"\n";
            count ++;
        }
        System.out.println(str);
        System.out.println("which would you like to see all movies for?");
        System.out.println("enter number");
        int num = scanner.nextInt();
        String lookCast = newCasts.get(num-1);
        ArrayList<String> castMovies = new ArrayList<>();
        for(int i =0;i<movieList.size();i++){
            if(movieList.get(i).getCast().indexOf(lookCast)!=-1){
                castMovies.add(movieList.get(i).getName());
            }
        }
        for(int i =0;i<castMovies.size();i++){
            for(int j=i+1;j<castMovies.size();j++){
                if(castMovies.get(i).compareToIgnoreCase(castMovies.get(j))>0){
                    String temp = castMovies.get(i);
                    castMovies.set(i,castMovies.get(j));
                    castMovies.set(j,temp);
                }
            }
        }
        String CastMoviestr = "";
        int CastMoviecount  =1;
        for(int i =0; i<castMovies.size();i++){
            CastMoviestr+= CastMoviecount+ ". "+castMovies.get(i)+"\n";
            CastMoviecount ++;
        }
        System.out.println(CastMoviestr);
        System.out.println("Which movie would you like to learn more about?");
        System.out.println("Enter number: ");
        int answer = scanner.nextInt();
        scanner.nextLine();


        for(int i =0;i<movieList.size();i++){
            if(movieList.get(i).getName().equals(castMovies.get(answer-1))){
                return movieList.get(i).dis();
            }
        }
        return "";
    }

}
