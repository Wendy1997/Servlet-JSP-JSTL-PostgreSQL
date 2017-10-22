package Service;

import DAO.MemberCardDAO;
import Model.MemberCard;

import java.sql.SQLException;
import java.util.List;

public class MemberCardServiceDatabase implements MemberCardService{
    MemberCardDAO memberCardDAO = new MemberCardDAO();

    @Override
    public MemberCard getMemberCard(String id) throws SQLException {
        return memberCardDAO.getMemberCard(id);
    }

    @Override
    public List<MemberCard> getAllMemberCard() throws SQLException {
        return memberCardDAO.getAllMemberCard();
    }

    @Override
    public void addMemberCard(MemberCard memberCard) throws SQLException {
        memberCardDAO.addMemberCard(memberCard);
    }

    @Override
    public void deleteMemberCard(String id) throws SQLException {
        memberCardDAO.deleteMemberCard(id);
    }

    @Override
    public void updateAccout(MemberCard memberCard) throws SQLException {
        memberCardDAO.updateMemberCard(memberCard);
    }
}
