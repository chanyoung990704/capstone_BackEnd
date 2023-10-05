package unit.capstone.repository.member;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import unit.capstone.domain.LikeMovies;
import unit.capstone.domain.QLikeMovies;
import unit.capstone.domain.QMember;

import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    QLikeMovies likeMovies = QLikeMovies.likeMovies;

    @Override
    public boolean isSavedLikeMovie(Long memberId, Long movieId) {
        JPAQuery<LikeMovies> query = queryFactory.selectFrom(likeMovies)
                .where(likeMovies.member.id.eq(memberId).and(likeMovies.movie.id.eq(movieId)));

        List<LikeMovies> result = query.fetch();

        if(!result.isEmpty())
            return true;
        else
            return false;


    }


}
