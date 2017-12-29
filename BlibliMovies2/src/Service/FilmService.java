package Service;

import DAO.FilmDAO;
import Model.*;

import java.sql.SQLException;
import java.util.List;

public interface FilmService {

    Film getFilm(String id, int storeid) throws SQLException;
    Film getFilmTrue(String id, int storeid) throws SQLException;
    List<Film> getAllFilm(int storeid) throws SQLException;
    List<Film> getAllFilmTrue(int storeid) throws SQLException;
    void addFilm(Film film) throws SQLException;
    void deleteFilm(String id, int storeid) throws SQLException;
    void updateFilm(Film film) throws SQLException;

    List<Studio> getAllStudio(int storeid) throws SQLException;
    List<Studio> getAllStudioTrue(int storeid) throws SQLException;
    Studio getStudio(String id, int storeid) throws SQLException;
    Studio getStudioTrue(String id, int storeid) throws SQLException;
    void addStudio(Studio studio) throws SQLException;
    void updateStudio(Studio studio) throws SQLException;
    void deleteSrudio(String id, int storeid) throws SQLException;

    void addScreeningTime (ScreeningTime screeningTime) throws SQLException;
    List<ScreeningTime> getAllScreeningTime (int storeid, String filmid) throws SQLException;
    List<ScreeningTime> getAllScreeningTimeTrue (int storeid, String filmid) throws SQLException;
    ScreeningTime getScreeningTime(String id, String filmId, int storeid) throws SQLException;
    ScreeningTime getScreeningTimeTrue(String id, String filmId, int storeid) throws SQLException;
    void updateScreeningTime(ScreeningTime screeningTime) throws SQLException;
    void deleteScreeningTime(String id, String filmid, int storeid) throws SQLException;

    FilmGenre getFilmGenre(String id, int storeid) throws SQLException;
    FilmGenre getFilmGenreTrue(String id, int storeid) throws SQLException;
    List<FilmGenre> getAllFilmGenre(int storeid) throws SQLException;
    List<FilmGenre> getAllFilmGenreTrue(int storeid) throws SQLException;
    void addFilmGenre(FilmGenre filmGenre) throws SQLException;
    void deleteFilmGenre(String filmGenre, int storeid) throws SQLException;
    void updateFilmGenre(FilmGenre filmGenre) throws SQLException;

    StudioType getStudioType(String id, int storeid) throws SQLException;
    StudioType getStudioTypeTrue(String id, int storeid) throws SQLException;
    List<StudioType> getAllStudioType(int storeid) throws SQLException;
    List<StudioType> getAllStudioTypeTrue(int storeid) throws SQLException;
    void addStudioType(StudioType studioType) throws SQLException;
    void deleteStudioType(String studioType, int storeid) throws SQLException;
    void updateStudioType(StudioType studioType) throws SQLException;

}