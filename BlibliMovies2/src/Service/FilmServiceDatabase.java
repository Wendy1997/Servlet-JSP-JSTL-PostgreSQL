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
    public List<Film> getAllFilm(int storeid) throws SQLException{
        return filmDAO.getAllFilm(storeid);
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
    public void updateFilm(Film film) throws SQLException{
        filmDAO.updateFilm(film);
    }

    @Override
    public List<Studio> getAllStudio(int storeid) throws SQLException {
        return studioDAO.getAllStudio(storeid);
    }

    @Override
    public Studio getStudio(String id, int storeid) throws SQLException {
        return studioDAO.getStudio(id, storeid);
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
    public void addScreeningTime(ScreeningTime screeningTime) throws SQLException {

        screeningTimeDAO.addScreeningTime(screeningTime);
    }

    @Override
    public List<ScreeningTime> getAllScreeningTime (int storeid, String filmid) throws SQLException{
        return screeningTimeDAO.getAllScreeningTime(storeid, filmid);
    }

    @Override
    public ScreeningTime getScreeningTime(String id, String filmId, int storeid) throws SQLException{
        return screeningTimeDAO.getScreeningTime(id, filmId, storeid);
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
    public FilmGenre getFilmGenre(String id, int storeid) throws SQLException {
        return filmGenreDAO.getFilmGenre(id, storeid);
    }

    @Override
    public List<FilmGenre> getAllFilmGenre(int storeid) throws SQLException {
        return filmGenreDAO.getAllFilmGenre(storeid);
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
    public void updateFilmGenre(FilmGenre filmGenre) throws SQLException {
        filmGenreDAO.updateFilmGenre(filmGenre);
    }

    @Override
    public StudioType getStudioType(String id, int storeid) throws SQLException {
        return studioTypeDAO.getStudioType(id, storeid);
    }

    @Override
    public List<StudioType> getAllStudioType(int storeid) throws SQLException {
        return studioTypeDAO.getAllStudioType(storeid);
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
    public void updateStudioType(StudioType studioType) throws SQLException {
        studioTypeDAO.updateStudioType(studioType);
    }
}
