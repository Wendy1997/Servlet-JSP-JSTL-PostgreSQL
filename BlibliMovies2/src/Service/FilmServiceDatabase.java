package Service;

import DAO.*;
import Model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FilmServiceDatabase implements FilmService {
    FilmDAO filmDAO;
    StudioDAO studioDAO;
    ScreeningTimeDAO screeningTimeDAO;
    FilmGenreDAO filmGenreDAO;
    StudioTypeDAO studioTypeDAO;

    @Override
    public Film getFilm(String id, int storeid) throws SQLException{
        filmDAO = new FilmDAO();
        return filmDAO.getFilm(id, storeid);
    }

    @Override
    public Film getFilmTrue(String id, int storeid) throws SQLException {
        filmDAO = new FilmDAO();
        return filmDAO.getFilmTrue(id, storeid);
    }

    @Override
    public List<Film> getAllFilm(int storeid, int offset) throws SQLException{
        filmDAO = new FilmDAO();
        return filmDAO.getAllFilm(storeid, offset);
    }

    @Override
    public int getCountAllFilm(int storeid) throws SQLException {
        filmDAO = new FilmDAO();
        return filmDAO.getCountAllFilm(storeid);
    }

    @Override
    public List<Film> getAllFilmTrue(int storeid, String dateNow) throws SQLException {
        filmDAO = new FilmDAO();
        return filmDAO.getAllFilmTrue(storeid, dateNow);
    }

    @Override
    public void addFilm(Film film) throws SQLException{
        filmDAO = new FilmDAO();
        filmDAO.addFilm(film);
    }

    @Override
    public void deleteFilm(String id, int storeid) throws SQLException{
        filmDAO = new FilmDAO();
        filmDAO.deleteFilm(id, storeid);

    }

    @Override
    public void retrieveFilm(String id, int storeid) throws SQLException {
        filmDAO = new FilmDAO();
        filmDAO.retrieveFilm(id, storeid);
    }

    @Override
    public void updateFilm(Film film) throws SQLException{
        filmDAO = new FilmDAO();
        filmDAO.updateFilm(film);
    }

    @Override
    public List<Studio> getAllStudio(int storeid, int offset) throws SQLException {
        studioDAO = new StudioDAO();
        return studioDAO.getAllStudio(storeid, offset);
    }

    @Override
    public int getCountAllStudio(int storeid) throws SQLException {
        studioDAO = new StudioDAO();
        return studioDAO.getCountAllStudio(storeid);
    }

    @Override
    public List<Studio> getAllStudioTrue(int storeid) throws SQLException {
        studioDAO = new StudioDAO();
        return studioDAO.getAllStudioTrue(storeid);
    }

    @Override
    public Studio getStudio(String id, int storeid) throws SQLException {
        studioDAO = new StudioDAO();
        return studioDAO.getStudio(id, storeid);
    }

    @Override
    public Studio getStudioTrue(String id, int storeid) throws SQLException {
        studioDAO = new StudioDAO();
        return studioDAO.getStudioTrue(id, storeid);
    }

    @Override
    public void addStudio(Studio studio) throws SQLException {
        studioDAO = new StudioDAO();
        studioDAO.addStudio(studio);
    }

    @Override
    public void updateStudio(Studio studio) throws SQLException {
        studioDAO = new StudioDAO();
        studioDAO.updateStudio(studio);
    }

    @Override
    public void deleteSrudio(String id, int storeid) throws SQLException {
        studioDAO = new StudioDAO();
        studioDAO.deleteStudio(id, storeid);
    }

    @Override
    public void retrieveSrudio(String id, int storeid) throws SQLException {
        studioDAO = new StudioDAO();
        studioDAO.retrieveStudio(id, storeid);
    }

    @Override
    public void addScreeningTime(ScreeningTime screeningTime) throws SQLException {
        screeningTimeDAO = new ScreeningTimeDAO();
        screeningTimeDAO.addScreeningTime(screeningTime);
    }

    @Override
    public List<ScreeningTime> getAllScreeningTime (int storeid, String filmid, int offset) throws SQLException{
        screeningTimeDAO = new ScreeningTimeDAO();
        return screeningTimeDAO.getAllScreeningTime(storeid, filmid, offset);
    }

    @Override
    public int getCountAllScreeningTime(int storeid, String filmid) throws SQLException {
        screeningTimeDAO = new ScreeningTimeDAO();
        return screeningTimeDAO.getCountAllScreeningTime(storeid, filmid);
    }

    @Override
    public List<ScreeningTime> getAllScreeningTimeTrue(int storeid, String filmid) throws SQLException {
        screeningTimeDAO = new ScreeningTimeDAO();
        return screeningTimeDAO.getAllScreeningTimeTrue(storeid, filmid);
    }

    @Override
    public ScreeningTime getScreeningTime(String id, String filmId, int storeid) throws SQLException{
        screeningTimeDAO = new ScreeningTimeDAO();
        return screeningTimeDAO.getScreeningTime(id, filmId, storeid);
    }

    @Override
    public ScreeningTime getScreeningTimeTrue(String id, String filmId, int storeid) throws SQLException {
        screeningTimeDAO = new ScreeningTimeDAO();
        return screeningTimeDAO.getScreeningTimeTrue(id, filmId, storeid);
    }

    @Override
    public void updateScreeningTime(ScreeningTime screeningTime) throws SQLException{
        screeningTimeDAO = new ScreeningTimeDAO();
        screeningTimeDAO.updateScreeningTime(screeningTime);
    }

    @Override
    public void deleteScreeningTime(String id, String filmid, int storeid) throws SQLException{
        screeningTimeDAO = new ScreeningTimeDAO();
        screeningTimeDAO.deleteScreeningTime(id, filmid, storeid);
    }

    @Override
    public void retrieveScreeningTime(String id, String filmid, int storeid) throws SQLException {
        screeningTimeDAO = new ScreeningTimeDAO();
        screeningTimeDAO.retrieveScreeningTime(id, filmid, storeid);
    }

    @Override
    public FilmGenre getFilmGenre(String id, int storeid) throws SQLException {
        filmGenreDAO = new FilmGenreDAO();
        return filmGenreDAO.getFilmGenre(id, storeid);
    }

    @Override
    public List<FilmGenre> getAllFilmGenre(int storeid, int offset) throws SQLException {
        filmGenreDAO = new FilmGenreDAO();
        return filmGenreDAO.getAllFilmGenre(storeid, offset);
    }

    @Override
    public List<FilmGenre> getShowAllFilmGenre(int storeid) throws SQLException {
        filmGenreDAO = new FilmGenreDAO();
        return filmGenreDAO.getShowAllFilmGenre(storeid);
    }

    @Override
    public int getCountAllFilmGenre(int storeid) throws SQLException {
        filmGenreDAO = new FilmGenreDAO();
        return filmGenreDAO.getCountAllFilmGenre(storeid);
    }

    @Override
    public List<FilmGenre> getAllFilmGenreTrue(int storeid) throws SQLException {
        filmGenreDAO = new FilmGenreDAO();
        return filmGenreDAO.getAllFilmGenreTrue(storeid);
    }

    @Override
    public void addFilmGenre(FilmGenre filmGenre) throws SQLException {
        filmGenreDAO = new FilmGenreDAO();
        filmGenreDAO.addFilmGenre(filmGenre);
    }

    @Override
    public void deleteFilmGenre(String filmGenre, int storeid) throws SQLException {
        filmGenreDAO = new FilmGenreDAO();
        filmGenreDAO.deleteFilmGenre(filmGenre, storeid);
    }

    @Override
    public void retrieveFilmGenre(String filmGenre, int storeid) throws SQLException {
        filmGenreDAO = new FilmGenreDAO();
        filmGenreDAO.retrieveFilmGenre(filmGenre, storeid);
    }

    @Override
    public void updateFilmGenre(FilmGenre filmGenre) throws SQLException {
        filmGenreDAO = new FilmGenreDAO();
        filmGenreDAO.updateFilmGenre(filmGenre);
    }

    @Override
    public StudioType getStudioType(String id, int storeid) throws SQLException {
        studioTypeDAO = new StudioTypeDAO();
        return studioTypeDAO.getStudioType(id, storeid);
    }

    @Override
    public List<StudioType> getAllStudioType(int storeid, int offset) throws SQLException {
        studioTypeDAO = new StudioTypeDAO();
        return studioTypeDAO.getAllStudioType(storeid, offset);
    }

    @Override
    public List<StudioType> getShowAllStudioType(int storeid) throws SQLException {
        studioTypeDAO = new StudioTypeDAO();
        return studioTypeDAO.getShowAllStudioType(storeid);
    }

    @Override
    public int getCountAllStudioType(int storeid) throws SQLException {
        studioTypeDAO = new StudioTypeDAO();
        return studioTypeDAO.getCountAllStudioType(storeid);
    }

    @Override
    public List<StudioType> getAllStudioTypeTrue(int storeid) throws SQLException {
        studioTypeDAO = new StudioTypeDAO();
        return studioTypeDAO.getAllStudioTypeTrue(storeid);
    }

    @Override
    public void addStudioType(StudioType studioType) throws SQLException {
        studioTypeDAO = new StudioTypeDAO();
        studioTypeDAO.addStudioType(studioType);
    }

    @Override
    public void deleteStudioType(String studioType, int storeid) throws SQLException {
        studioTypeDAO = new StudioTypeDAO();
        studioTypeDAO.deleteStudioType(studioType, storeid);
    }

    @Override
    public void retrieveStudioType(String studioType, int storeid) throws SQLException {
        studioTypeDAO = new StudioTypeDAO();
        studioTypeDAO.retrieveStudioType(studioType, storeid);
    }

    @Override
    public void updateStudioType(StudioType studioType) throws SQLException {
        studioTypeDAO = new StudioTypeDAO();
        studioTypeDAO.updateStudioType(studioType);
    }
}
