package Service;

import DAO.MemberCardDAO;
import DAO.MemberGenderDAO;
import Model.MemberCard;
import Model.MemberGender;

import java.sql.SQLException;
import java.util.List;

public class MemberCardServiceDatabase implements MemberCardService{
    MemberCardDAO memberCardDAO = new MemberCardDAO();
    MemberGenderDAO memberGenderDAO = new MemberGenderDAO();

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

    @Override
    public MemberGender getMemberGender(String id, String storename) throws SQLException {
        return memberGenderDAO.getMemberGender(id, storename);
    }

    @Override
    public List<MemberGender> getAllMemberGender(String storename) throws SQLException {
        return memberGenderDAO.getAllMemberGender(storename);
    }

    @Override
    public void addMemberGender(MemberGender memberGender) throws SQLException {
        memberGenderDAO.addMemberGender(memberGender);
    }

    @Override
    public void deleteMemberGender(String memberGender, String storename) throws SQLException {
        memberGenderDAO.deleteMemberGender(memberGender, storename);
    }

    @Override
    public void updateMemberGender(MemberGender memberGender) throws SQLException {
        memberGenderDAO.updateMemberGender(memberGender);
    }
}
