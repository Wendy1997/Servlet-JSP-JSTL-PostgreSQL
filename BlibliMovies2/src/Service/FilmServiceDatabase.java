package Service;

import DAO.*;
import Model.*;

import java.sql.SQLException;
import java.util.List;

public class FilmServiceDatabase implements FilmService {
    FilmDAO filmDAO = new FilmDAO();
    StudioDAO studioDAO = new StudioDAO();
    ScreeningTimeDAO screeningTimeDAO = new ScreeningTimeDAO();
    FilmGenreDAO filmGenreDAO = new FilmGenreDAO();
    StudioTypeDAO studioTypeDAO = new StudioTypeDAO();

    @Override
    public Film getFilm(String id, int storeid) throws SQLException{
        return filmDAO.getFilm(id, storeid);
    }

    @Override
    public Film getFilmTrue(String id, int storeid) throws SQLException {
        return filmDAO.getFilmTrue(id, storeid);
    }

    @Override
    public List<Film> getAllFilm(int storeid, int offset) throws SQLException{
        return filmDAO.getAllFilm(storeid, offset);
    }

    @Override
    public int getCountAllFilm(int storeid) throws SQLException {
        return filmDAO.getCountAllFilm(storeid);
    }

    @Override
    public List<Film> getAllFilmTrue(int storeid) throws SQLException {
        return filmDAO.getAllFilmTrue(storeid);
    }

    @Override
    public void addFilm(Film film) throws SQLException{
        filmDAO.addFilm(film);
    }

    @Override
    public void deleteFilm(String id, int storeid) throws SQLException{
        filmDAO.deleteFilm(id, storeid);

    }

    @Override
    public void retrieveFilm(String id, int storeid) throws SQLException {
        filmDAO.retrieveFilm(id, storeid);
    }

    @Override
    public void updateFilm(Film film) throws SQLException{
        filmDAO.updateFilm(film);
    }

    @Override
    public List<Studio> getAllStudio(int storeid, int offset) throws SQLException {
        return studioDAO.getAllStudio(storeid, offset);
    }

    @Override
    public int getCountAllStudio(int storeid) throws SQLException {
        return studioDAO.getCountAllStudio(storeid);
    }

    @Override
    public List<Studio> getAllStudioTrue(int storeid) throws SQLException {
        return studioDAO.getAllStudioTrue(storeid);
    }

    @Override
    public Studio getStudio(String id, int storeid) throws SQLException {
        return studioDAO.getStudio(id, storeid);
    }

    @Override
    public Studio getStudioTrue(String id, int storeid) throws SQLException {
        return studioDAO.getStudioTrue(id, storeid);
    }

    @Override
    public void addStudio(Studio studio) throws SQLException {
        studioDAO.addStudio(studio);
    }

    @Override
    public void updateStudio(Studio studio) throws SQLException {
        studioDAO.updateStudio(studio);
    }

    @Override
    public void deleteSrudio(String id, int storeid) throws SQLException {
        studioDAO.deleteStudio(id, storeid);
    }

    @Override
    public void retrieveSrudio(String id, int storeid) throws SQLException {
        studioDAO.retrieveStudio(id, storeid);
    }

    @Override
    public void addScreeningTime(ScreeningTime screeningTime) throws SQLException {
        screeningTimeDAO.addScreeningTime(screeningTime);
    }

    @Override
    public List<ScreeningTime> getAllScreeningTime (int storeid, String filmid, int offset) throws SQLException{
        return screeningTimeDAO.getAllScreeningTime(storeid, filmid, offset);
    }

    @Override
    public int getCountAllScreeningTime(int storeid, String filmid) throws SQLException {
        return screeningTimeDAO.getCountAllScreeningTime(storeid, filmid);
    }

    @Override
    public List<ScreeningTime> getAllScreeningTimeTrue(int storeid, String filmid) throws SQLException {
        return screeningTimeDAO.getAllScreeningTimeTrue(storeid, filmid);
    }

    @Override
    public ScreeningTime getScreeningTime(String id, String filmId, int storeid) throws SQLException{
        return screeningTimeDAO.getScreeningTime(id, filmId, storeid);
    }

    @Override
    public ScreeningTime getScreeningTimeTrue(String id, String filmId, int storeid) throws SQLException {
        return screeningTimeDAO.getScreeningTimeTrue(id, filmId, storeid);
    }

    @Override
    public void updateScreeningTime(ScreeningTime screeningTime) throws SQLException{
        screeningTimeDAO.updateScreeningTime(screeningTime);
    }

    @Override
    public void deleteScreeningTime(String id, String filmid, int storeid) throws SQLException{
        screeningTimeDAO.deleteScreeningTime(id, filmid, storeid);
    }

    @Override
    public void retrieveScreeningTime(String id, String filmid, int storeid) throws SQLException {
        screeningTimeDAO.retrieveScreeningTime(id, filmid, storeid);
    }

    @Override
    public FilmGenre getFilmGenre(String id, int storeid) throws SQLException {
        return filmGenreDAO.getFilmGenre(id, storeid);
    }

    @Override
    public List<FilmGenre> getAllFilmGenre(int storeid, int offset) throws SQLException {
        return filmGenreDAO.getAllFilmGenre(storeid, offset);
    }

    @Override
    public List<FilmGenre> getShowAllFilmGenre(int storeid) throws SQLException {
        return filmGenreDAO.getShowAllFilmGenre(storeid);
    }

    @Override
    public int getCountAllFilmGenre(int storeid) throws SQLException {
        return filmGenreDAO.getCountAllFilmGenre(storeid);
    }

    @Override
    public List<FilmGenre> getAllFilmGenreTrue(int storeid) throws SQLException {
        return filmGenreDAO.getAllFilmGenreTrue(storeid);
    }

    @Override
    public void addFilmGenre(FilmGenre filmGenre) throws SQLException {
        filmGenreDAO.addFilmGenre(filmGenre);
    }

    @Override
    public void deleteFilmGenre(String filmGenre, int storeid) throws SQLException {
        filmGenreDAO.deleteFilmGenre(filmGenre, storeid);
    }

    @Override
    public void retrieveFilmGenre(String filmGenre, int storeid) throws SQLException {
        filmGenreDAO.retrieveFilmGenre(filmGenre, storeid);
    }

    @Override
    public void updateFilmGenre(FilmGenre filmGenre) throws SQLException {
        filmGenreDAO.updateFilmGenre(filmGenre);
    }

    @Override
    public StudioType getStudioType(String id, int storeid) throws SQLException {
        return studioTypeDAO.getStudioType(id, storeid);
    }

    @Override
    public List<StudioType> getAllStudioType(int storeid, int offset) throws SQLException {
        return studioTypeDAO.getAllStudioType(storeid, offset);
    }

    @Override
    public List<StudioType> getShowAllStudioType(int storeid) throws SQLException {
        return studioTypeDAO.getShowAllStudioType(storeid);
    }

    @Override
    public int getCountAllStudioType(int storeid) throws SQLException {
        return studioTypeDAO.getCountAllStudioType(storeid);
    }

    @Override
    public List<StudioType> getAllStudioTypeTrue(int storeid) throws SQLException {
        return studioTypeDAO.getAllStudioTypeTrue(storeid);
    }

    @Override
    public void addStudioType(StudioType studioType) throws SQLException {
        studioTypeDAO.addStudioType(studioType);
    }

    @Override
    public void deleteStudioType(String studioType, int storeid) throws SQLException {
        studioTypeDAO.deleteStudioType(studioType, storeid);
    }

    @Override
    public void retrieveStudioType(String studioType, int storeid) throws SQLException {
        studioTypeDAO.retrieveStudioType(studioType, storeid);
    }

    @Override
    public void updateStudioType(StudioType studioType) throws SQLException {
        studioTypeDAO.updateStudioType(studioType);
    }
}
