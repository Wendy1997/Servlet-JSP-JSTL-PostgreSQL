package Service;

import DAO.FilmDAO;
import DAO.StudioDAO;
import Model.Film;
import Model.Studio;

import java.sql.SQLException;
import java.util.List;

public class FilmServiceDatabase implements FilmService {
    FilmDAO filmDAO = new FilmDAO();
    StudioDAO studioDAO = new StudioDAO();

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
}
