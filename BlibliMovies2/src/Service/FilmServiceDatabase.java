package Service;

import DAO.FilmDAO;
import DAO.ScreeningTimeDAO;
import DAO.StudioDAO;
import Model.Film;
import Model.ScreeningTime;
import Model.Studio;

import java.sql.SQLException;
import java.util.List;

public class FilmServiceDatabase implements FilmService {
    FilmDAO filmDAO = new FilmDAO();
    StudioDAO studioDAO = new StudioDAO();
    ScreeningTimeDAO screeningTimeDAO = new ScreeningTimeDAO();

    @Override
    public Film getFilm(String id, String storename) throws SQLException{
        return filmDAO.getFilm(id, storename);
    }

    @Override
    public List<Film> getAllFilm(String storename) throws SQLException{
        return filmDAO.getAllFilm(storename);
    }

    @Override
    public void addFilm(Film film) throws SQLException{
        filmDAO.addFilm(film);
    }

    @Override
    public void deleteFilm(String id, String storename) throws SQLException{
        filmDAO.deleteFilm(id, storename);

    }

    @Override
    public void updateFilm(Film film) throws SQLException{
        filmDAO.updateFilm(film);
    }

    @Override
    public List<Studio> getAllStudio(String storename) throws SQLException {
        return studioDAO.getAllStudio(storename);
    }

    @Override
    public Studio getStudio(String id, String storename) throws SQLException {
        return studioDAO.getStudio(id, storename);
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
    public void deleteSrudio(String id, String storename) throws SQLException {
        studioDAO.deleteStudio(id, storename);
    }

    @Override
    public void addScreeningTime(ScreeningTime screeningTime) throws SQLException {

        screeningTimeDAO.addScreeningTime(screeningTime);
    }

    @Override
    public List<ScreeningTime> getAllScreeningTime (String storename, String filmid) throws SQLException{
        return screeningTimeDAO.getAllScreeningTime(storename, filmid);
    }

    @Override
    public ScreeningTime getScreeningTime(String id, String filmId, String storename) throws SQLException{
        return screeningTimeDAO.getScreeningTime(id, filmId, storename);
    }

    @Override
    public void updateScreeningTime(ScreeningTime screeningTime) throws SQLException{
        screeningTimeDAO.updateScreeningTime(screeningTime);
    }

    @Override
    public void deleteScreeningTime(String id, String filmid, String storename) throws SQLException{
        screeningTimeDAO.deleteScreeningTime(id, filmid, storename);
    }
}
