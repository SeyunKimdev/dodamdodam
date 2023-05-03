package com.app.dodamdodam.entity.purchase;

import com.app.dodamdodam.entity.board.Reply;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "TBL_PURCHASE_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseReview extends Reply {
    private Integer reviewGrade;

    @ManyToOne
    @JoinColumn(name = "PURCHASE_BOARD_ID")
    private PurchaseBoard purchaseBoard;

}