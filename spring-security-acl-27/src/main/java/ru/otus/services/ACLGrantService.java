package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ACLGrantService {

    @Autowired
    private MutableAclService mutableAclService;

    public void applyACLGranting(ObjectIdentity identity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Sid owner = new PrincipalSid(authentication );
        final Sid roleAdmin = new GrantedAuthoritySid("ROLE_ADMIN");
        final Sid roleUser = new GrantedAuthoritySid("ROLE_USER");
        MutableAcl acl = mutableAclService.createAcl(identity);
        acl.setOwner( owner );

//        acl.insertAce(acl.getEntries().size(),BasePermission.READ, admin, true);
//        mutableAclService.updateAcl( acl );
        acl.insertAce(acl.getEntries().size(), BasePermission.READ, roleAdmin,true);
        acl.insertAce(acl.getEntries().size(),BasePermission.WRITE, roleAdmin, true);
        acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, roleAdmin, true);
        acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, roleAdmin, true );
        acl.insertAce(acl.getEntries().size(), BasePermission.READ, roleUser, true );

        mutableAclService.updateAcl( acl );
//        acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, admin, true);
//        mutableAclService.updateAcl( acl );
//        acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, roleAdmin, true );
//        mutableAclService.updateAcl( acl );
//        acl.insertAce(acl.getEntries().size(),BasePermission.READ, user, true);
//        mutableAclService.updateAcl( acl );
    }

}
