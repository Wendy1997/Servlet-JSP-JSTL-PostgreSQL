package Service;

import Model.MemberCard;

import java.sql.SQLException;
import java.util.List;

public interface MemberCardService {

    MemberCard getMemberCard(String id, String storename) throws SQLException;
    List<MemberCard> getAllMemberCard(String storename) throws SQLException;
    void addMemberCard(MemberCard memberCard) throws SQLException;
    void deleteMemberCard(String id, String storename) throws SQLException;
    void updateAccout(MemberCard memberCard) throws SQLException;
}
