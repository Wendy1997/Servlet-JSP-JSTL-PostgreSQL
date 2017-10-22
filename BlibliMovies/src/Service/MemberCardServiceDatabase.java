package Service;

import DAO.MemberCardDAO;
import Model.MemberCard;

import java.sql.SQLException;
import java.util.List;

public class MemberCardServiceDatabase implements MemberCardService{
    MemberCardDAO memberCardDAO = new MemberCardDAO();

    @Override
    public MemberCard getMemberCard(String id, String storename) throws SQLException {
        return memberCardDAO.getMemberCard(id, storename);
    }

    @Override
    public List<MemberCard> getAllMemberCard(String storename) throws SQLException {
        return memberCardDAO.getAllMemberCard(storename);
    }

    @Override
    public void addMemberCard(MemberCard memberCard) throws SQLException {
        memberCardDAO.addMemberCard(memberCard);
    }

    @Override
    public void deleteMemberCard(String id, String storename) throws SQLException {
        memberCardDAO.deleteMemberCard(id, storename);
    }

    @Override
    public void updateAccout(MemberCard memberCard) throws SQLException {
        memberCardDAO.updateMemberCard(memberCard);
    }
}
