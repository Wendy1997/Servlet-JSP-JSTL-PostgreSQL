package Service;

import DAO.FilmDAO;
import Model.*;

import java.sql.SQLException;
import java.util.List;

public interface FilmService {

    Film getFilm(String id, String storename) throws SQLException;
    List<Film> getAllFilm(String storename) throws SQLException;
    void addFilm(Film film) throws SQLException;
    void deleteFilm(String id, String storename) throws SQLException;
    void updateFilm(Film film) throws SQLException;

    List<Studio> getAllStudio(String storename) throws SQLException;
    Studio getStudio(String id, String storename) throws SQLException;
    void addStudio(Studio studio) throws SQLException;
    void updateStudio(Studio studio) throws SQLException;
    void deleteSrudio(String id, String storename) throws SQLException;

    void addScreeningTime (ScreeningTime screeningTime) throws SQLException;
    List<ScreeningTime> getAllScreeningTime (String storename, String filmid) throws SQLException;
    ScreeningTime getScreeningTime(String id, String filmId, String storename) throws SQLException;
    void updateScreeningTime(ScreeningTime screeningTime) throws SQLException;
    void deleteScreeningTime(String id, String filmid, String storename) throws SQLException;

    FilmGenre getFilmGenre(String id, String storename) throws SQLException;
    List<FilmGenre> getAllFilmGenre(String storename) throws SQLException;
    void addFilmGenre(FilmGenre filmGenre) throws SQLException;
    void deleteFilmGenre(String filmGenre, String storename) throws SQLException;
    void updateFilmGenre(FilmGenre filmGenre) throws SQLException;

    StudioType getStudioType(String id, String storename) throws SQLException;
    List<StudioType> getAllStudioType(String storename) throws SQLException;
    void addStudioType(StudioType studioType) throws SQLException;
    void deleteStudioType(String studioType, String storename) throws SQLException;
    void updateStudioType(StudioType studioType) throws SQLException;

}