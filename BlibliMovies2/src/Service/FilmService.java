package Service;

import DAO.FilmDAO;
import Model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface FilmService {

    Film getFilm(String id, int storeid) throws SQLException;
    Film getFilmTrue(String id, int storeid) throws SQLException;
    List<Film> getAllFilm(int storeid, int offset) throws SQLException;
    int getCountAllFilm(int storeid) throws SQLException;
    List<Film> getAllFilmTrue(int storeid, String dateNow) throws SQLException;
    void addFilm(Film film) throws SQLException;
    void deleteFilm(String id, int storeid) throws SQLException;
    void retrieveFilm(String id, int storeid) throws SQLException;
    void updateFilm(Film film) throws SQLException;

    List<Studio> getAllStudio(int storeid, int offset) throws SQLException;
    int getCountAllStudio(int storeid) throws SQLException;
    List<Studio> getAllStudioTrue(int storeid) throws SQLException;
    Studio getStudio(String id, int storeid) throws SQLException;
    Studio getStudioTrue(String id, int storeid) throws SQLException;
    void addStudio(Studio studio) throws SQLException;
    void updateStudio(Studio studio) throws SQLException;
    void deleteSrudio(String id, int storeid) throws SQLException;
    void retrieveSrudio(String id, int storeid) throws SQLException;

    void addScreeningTime (ScreeningTime screeningTime) throws SQLException;
    List<ScreeningTime> getAllScreeningTime (int storeid, String filmid, int offset) throws SQLException;
    int getCountAllScreeningTime (int storeid, String filmid) throws SQLException;
    List<ScreeningTime> getAllScreeningTimeTrue (int storeid, String filmid) throws SQLException;
    ScreeningTime getScreeningTime(String id, String filmId, int storeid) throws SQLException;
    ScreeningTime getScreeningTimeTrue(String id, String filmId, int storeid) throws SQLException;
    void updateScreeningTime(ScreeningTime screeningTime) throws SQLException;
    void deleteScreeningTime(String id, String filmid, int storeid) throws SQLException;
    void retrieveScreeningTime(String id, String filmid, int storeid) throws SQLException;

    FilmGenre getFilmGenre(String id, int storeid) throws SQLException;
    List<FilmGenre> getAllFilmGenre(int storeid, int offset) throws SQLException;
    List<FilmGenre> getShowAllFilmGenre(int storeid) throws SQLException;
    int getCountAllFilmGenre(int storeid) throws SQLException;
    List<FilmGenre> getAllFilmGenreTrue(int storeid) throws SQLException;
    void addFilmGenre(FilmGenre filmGenre) throws SQLException;
    void deleteFilmGenre(String filmGenre, int storeid) throws SQLException;
    void retrieveFilmGenre(String filmGenre, int storeid) throws SQLException;
    void updateFilmGenre(FilmGenre filmGenre) throws SQLException;

    StudioType getStudioType(String id, int storeid) throws SQLException;
    List<StudioType> getAllStudioType(int storeid, int offset) throws SQLException;
    List<StudioType> getShowAllStudioType(int storeid) throws SQLException;
    int getCountAllStudioType(int storeid) throws SQLException;
    List<StudioType> getAllStudioTypeTrue(int storeid) throws SQLException;
    void addStudioType(StudioType studioType) throws SQLException;
    void deleteStudioType(String studioType, int storeid) throws SQLException;
    void retrieveStudioType(String studioType, int storeid) throws SQLException;
    void updateStudioType(StudioType studioType) throws SQLException;

}