package com.kata.mower.application;

import com.kata.mower.domain.Grown;
import com.kata.mower.domain.Mower;
import com.kata.mower.domain.Orientation;
import com.kata.mower.domain.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MowerTest {


    @Test
    public void should_initialize_mower_with_position_and_orientation() {
        // given
        Position initialPosition = Position.of(5, 5);

        // when
        Mower mower = Mower.of(initialPosition, "N");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(initialPosition);
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.NORTH);
    }


    @Test
    public void should_change_orientation_given_instruction_left_and_orientation_north() {
        //given
        Position initialPosition = Position.of(5, 5);
        Mower mower = Mower.of(initialPosition, "N");

        // when
        mower.executeSingleInstruction("L");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(initialPosition);
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.WEST);
    }

    @Test
    public void should_change_orientation_given_instruction_left_and_orientation_west() {
        //given
        Position initialPosition = Position.of(5, 5);
        Mower mower = Mower.of(initialPosition, "W");

        // when
        mower.executeSingleInstruction("L");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(initialPosition);
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.SOUTH);
    }

    @Test
    public void should_throw_error_given_unknown_instruction() {
        //given
        Position initialPosition = Position.of(5, 5);
        Mower mower = Mower.of(initialPosition, "W");

        //when & then
        assertThrows(IllegalArgumentException.class, () -> mower.executeSingleInstruction("T"));

    }

    @Test
    public void should_change_orientation_given_instruction_right_and_orientation_north() {
        //given
        Position initialPosition = Position.of(5, 5);
        Mower mower = Mower.of(initialPosition, "N");

        // when
        mower.executeSingleInstruction("R");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(initialPosition);
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    public void should_move_forward_north_given_F_instruction_and_north_initial_position() {
        //given
        Position initialPosition = Position.of(5, 5);
        Mower mower = Mower.of(initialPosition, "N");

        // when
        mower.executeSingleInstruction("F");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(Position.of(5, 6));
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.NORTH);
    }

    @Test
    public void should_move_forward_east_given_F_instruction_and_east_initial_position() {
        //given
        Position initialPosition = Position.of(5, 5);
        Mower mower = Mower.of(initialPosition, "E");

        // when
        mower.executeSingleInstruction("F");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(Position.of(6, 5));
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    public void should_stay_at_position_given_mower_at_growwn_bounds_and_try_to_move_forward() {
        //given
        Position initialPosition = Position.of(0, 5);
        Mower mower = Mower.of(initialPosition, "N", Grown.of(5, 5));

        // when
        mower.executeSingleInstruction("F");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(Position.of(0, 5));
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.NORTH);
    }


    @Test
    public void should_move_forward_south_given_F_instruction_and_south_initial_position() {
        //given
        Position initialPosition = Position.of(5, 5);
        Mower mower = Mower.of(initialPosition, "S");

        // when
        mower.executeSingleInstruction("F");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(Position.of(5, 4));
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.SOUTH);
    }

    @Test
    public void should_execute_several_instructions() {
        //given
        Position initialPosition = Position.of(5, 5);
        Mower mower = Mower.of(initialPosition, "S");

        // when
        mower.executeInstructions("FL");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(Position.of(5, 4));
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    public void should_return_error_given_mower_initial_position_out_of_bound_of_grown_with_x_position() {
        //given
        Position initialPosition = Position.of(-2, 1);

        //when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Mower.of(initialPosition, "E", Grown.of(3, 5)));
        assertThat(e).hasMessage("mower must start inside the grown");
    }

    @Test
    public void should_return_error_given_mower_initial_position_out_of_bound_of_grown_with_y_position() {
        //given
        Position initialPosition = Position.of(2, 5);

        //when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Mower.of(initialPosition, "E", Grown.of(4, 4)));
        assertThat(e).hasMessage("mower must start inside the grown");
    }

    @Test
    public void should_not_move_given_new_position_out_bound_of_grown_and_going_east() {
        //given
        Position initialPosition = Position.of(5, 3);
        Mower mower = Mower.of(initialPosition, "E", Grown.of(5, 5));

        // when
        mower.executeInstructions("F");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(Position.of(5, 3));
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    public void should_not_move_given_new_position_out_bound_of_grown_and_going_south() {
        //given
        Position initialPosition = Position.of(5, 0);
        Mower mower = Mower.of(initialPosition, "S", Grown.of(5, 5));

        // when
        mower.executeInstructions("F");

        // then
        assertThat(mower.getCurrentPosition()).isEqualTo(Position.of(5, 0));
        assertThat(mower.getCurrentOrientation()).isEqualTo(Orientation.SOUTH);
    }


    @Test
    public void should_move_two_mowers() {
        //given
        Grown grown = Grown.of(5, 5);
        Position initialPosition = Position.of(1, 2);
        Mower firstMower = Mower.of(initialPosition, "N", grown);

        Mower secondMower = Mower.of(Position.of(3, 3), "E", grown);
        // when
        firstMower.executeInstructions("LFLFLFLFF");
        secondMower.executeInstructions("FFRFFRFRRF");

        // then
        assertThat(firstMower.getCurrentPosition()).isEqualTo(Position.of(1, 3));
        assertThat(firstMower.getCurrentOrientation()).isEqualTo(Orientation.NORTH);
        assertThat(secondMower.getCurrentPosition()).isEqualTo(Position.of(5, 1));
        assertThat(secondMower.getCurrentOrientation()).isEqualTo(Orientation.EAST);

    }

    @Test
    public void should_move_two_mowers_given_collisions_between_mowers() {

        //given
        Grown grown = Grown.of(5, 5);
        Position initialPosition = Position.of(1, 2);
        Mower firstMower = Mower.of(initialPosition, "N", grown);
        Mower secondMower = Mower.of(Position.of(1, 3), "N", grown);

        // when
        firstMower.executeInstructions("F");
        secondMower.executeInstructions("F");

        // then
        assertThat(firstMower.getCurrentPosition()).isEqualTo(Position.of(1, 2));
        assertThat(firstMower.getCurrentOrientation()).isEqualTo(Orientation.NORTH);
        assertThat(secondMower.getCurrentPosition()).isEqualTo(Position.of(1, 4));
        assertThat(secondMower.getCurrentOrientation()).isEqualTo(Orientation.NORTH);

    }


}