package com.movie.app.service;


import com.movie.app.dto.MovieDto;
import com.movie.app.dto.UserDto;
import com.movie.app.model.Auth;
import com.movie.app.model.Movie;
import com.movie.app.model.Rating;
import com.movie.app.model.User;
import com.movie.app.repository.AuthRepository;
import com.movie.app.repository.UserRepository;
import com.movie.app.utils.RandomStringGenerator;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repos;

    //Need second injector for roles
    private final AuthRepository rolerepos;
    private final MovieService movieService;

    public UserService(UserRepository repos, AuthRepository rolerepos, MovieService movieService) {
        this.repos = repos;
        this.rolerepos = rolerepos;
        this.movieService = movieService;

    }


    //create User
// create User
    public UserDto createUser(UserDto userDto) {
        // Check if a user with the given username already exists
        if (repos.findByUsername(userDto.username) != null) {
            throw new RuntimeException("User with the username already exists.");
        }

        // Create a new user role
        Auth userRole = new Auth();
        userRole.setName("ROLE_USER"); // Set the identifier value
        rolerepos.save(userRole);

        // Map the UserDto to a new User entity using the DTO to User mapper
        User user = dtoToUser(userDto);

        // Set the roles directly from the userRole
        user.setAuthorities(Collections.singleton(userRole));

        // Generate and set API key
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        user.setApikey(randomString);

        // Save the user to the repository
        repos.save(user);

        // Map the saved user entity to a UserDto using the User to DTO mapper
        userDto.id = user.getId();
        userDto.apikey = user.getApikey();
        return userDto(user);
    }



    //Get autority roles of user
    public Set<String> getAuthorities(String username) {
        User user = repos.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Extract authority names from the user's authorities
        return user.getAuthorities().stream()
                .map(Auth::getName)
                .collect(Collectors.toSet());
    }

    //    Verwijdert een autoriteit van een gebruiker op basis van de gebruikersnaam en autoriteit.
    public void removeAuthority(String username, String authority) {
        User user = repos.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Auth authorityToRemove = user.getAuthorities().stream()
                .filter(a -> a.getName().equalsIgnoreCase(authority))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Authority not found for user: " + username));

        user.getAuthorities().remove(authorityToRemove);
        repos.save(user);
    }




    //getUser By Id
    public UserDto getUserById(Long userId) {
        // Retrieve the user entity by ID from the repository
        Optional<User> optionalUser = repos.findById(userId);

        // Map the user entity to a UserDto if it exists
        return optionalUser.map(this::userDto).orElse(null);
    }

    // Set favorite movies for a user
    public UserDto setFavoriteMovies(Long userId, Set<MovieDto> favoriteMovies) {
        User existingUser = repos.findById(userId).orElse(null);

        if (existingUser != null) {
            // Convert MovieDto objects to Movie entities
            Set<Movie> favoriteMovieEntities = favoriteMovies.stream()
                    .map(movieDto -> movieService.getMovieById(movieDto.id))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            existingUser.setFavoriteMovie(favoriteMovieEntities);

            // Ensure that the user ID is not null
            if (existingUser.getId() != null) {
                repos.save(existingUser);
                return userDto(existingUser);
            } else {
                throw new RuntimeException("User ID is null after setting favorite movies.");
            }
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }


    // Get favorite movies for a user
    public Set<MovieDto> getUserFavoriteMovies(Long userId) {
        User existingUser = repos.findById(userId).orElse(null);

        if (existingUser != null) {
            // Map the favorite movies to MovieDto
            return existingUser.getFavoriteMovie().stream()
                    .map(movieService::movieDto)
                    .collect(Collectors.toSet());
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }


    // Set seen movies for a user
    public UserDto setSeenMovies(Long userId, Set<MovieDto> seenMovies) {
        User existingUser = repos.findById(userId).orElse(null);

        if (existingUser != null) {
            // Convert MovieDto objects to Movie entities
            Set<Movie> seenMovieEntities = seenMovies.stream()
                    .map(movieDto -> movieService.getMovieById(movieDto.id))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            // Add the movies to the user's seen set
            existingUser.setMoviesSeen(seenMovieEntities);

            // Save the changes
            repos.save(existingUser);

            // Return the updated user DTO
            return userDto(existingUser);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }


    // Get seen movies for a user
    public Set<MovieDto> getUserSeenMovies(Long userId) {
        User existingUser = repos.findById(userId).orElse(null);

        if (existingUser != null) {
            // Map the seen movies to MovieDto
            return existingUser.getMoviesSeen().stream()
                    .map(movieService::movieDto)
                    .collect(Collectors.toSet());
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    //set the rating for a movie made by the user
    public void rateMovie(Long userId, Long movieId, double rating) {
        User existingUser = repos.findById(userId).orElse(null);
        Movie existingMovie = movieService.getMovieById(movieId);

        if (existingUser != null && existingMovie != null) {
            // Check if the user has already rated the movie
            if (existingUser.getMoviesRated().stream().anyMatch(movie -> movie.getId().equals(movieId))) {
                throw new RuntimeException("User has already rated this movie.");
            }
            // Create a new Rating entity
            Rating newRating = new Rating();
            newRating.setUser(existingUser);
            newRating.setMovie(existingMovie);
            newRating.setRating(rating);

            // Add the new rating to the user's set of rated movies
            existingUser.getMoviesRated().add(existingMovie);
            existingUser.getRatings().add(newRating);

            // Save the changes
            repos.save(existingUser);
        } else {
            throw new RuntimeException("User or Movie not found with provided IDs.");
        }
    }



    //user DTO MAPPER
    public UserDto userDto(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.address = user.getAddress();
        userDto.apikey = user.getApikey();
        userDto.email = user.getEmail();
        userDto.profileUrl = user.getProfileUrl();
        userDto.moviesSeen = user.getMoviesSeen();
        userDto.moviesRated = user.getMoviesRated();
        userDto.favoriteMovie = user.getFavoriteMovie();
        userDto.authorities = user.getAuthorities();
        return userDto;
    }

    // DTO TO USER OBJECT MAPPING
    public User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username);
        user.setPassword(userDto.password);
        user.setAddress(userDto.address);
        user.setApikey(userDto.apikey);
        user.setEmail(userDto.email);
        user.setProfileUrl(userDto.profileUrl);
        user.setMoviesRated(userDto.moviesRated);
        user.setFavoriteMovie(userDto.favoriteMovie);
        user.setMoviesSeen(userDto.moviesSeen);
        return user;
    }
}