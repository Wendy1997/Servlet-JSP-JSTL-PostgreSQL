package Service;

import Model.MemberCard;

import java.sql.SQLException;
import java.util.List;

public interface MemberCardService {

    MemberCard getMemberCard(String id) throws SQLException;
    List<MemberCard> getAllMemberCard() throws SQLException;
    void addMemberCard(MemberCard memberCard) throws SQLException;
    void deleteMemberCard(String id) throws SQLException;
    void updateAccout(MemberCard memberCard) throws SQLException;
}
