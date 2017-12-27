package com.ahid.game;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by cccc on 12/27/2017.
 */
public class GameTest {

    @Test
    public void markAndGetTest() throws InvalidPointException {
        GameObject go = new GameObject(10);
        assertNotNull(go);
        go = go.markAndGet(new GameObject.Point(0,0), Boolean.TRUE);
        assertNotNull(go);
        assertEquals(go.getValue(new GameObject.Point(0, 0)), Boolean.TRUE);
        assertNull(go.getValue(new GameObject.Point(0, 1)));

        go = go.markAndGet(new GameObject.Point(0,3), Boolean.FALSE);
        assertEquals(go.getValue(new GameObject.Point(0, 3)), Boolean.FALSE);
    }

    @Test(expected = InvalidPointException.class)
    public void indexInvalidTest() throws InvalidPointException {
        GameObject go = new GameObject(10);
        assertNotNull(go);
        go = go.markAndGet(new GameObject.Point(0,25), Boolean.TRUE);
    }

    @Test
    public void getNotMarkedTest() throws InvalidPointException {
        GameObject go = new GameObject(10);
        assertNotNull(go);
        go = go.markAndGet(new GameObject.Point(0,0), Boolean.TRUE);
        go = go.markAndGet(new GameObject.Point(0,3), Boolean.FALSE);
        assertEquals(go.getNotMarkedPoints().size(), 98);

        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(0,9),
                new GameObject.Point(1,0),
                new GameObject.Point(2,0),
                new GameObject.Point(3,0),
                new GameObject.Point(3,7),
                new GameObject.Point(39,97)
        ), Boolean.TRUE);
        System.out.println(go);
        assertEquals(go.getNotMarkedPoints().size(), 93);
    }

    @Test
    public void randomTest() throws InvalidPointException {
        GameObject go = GameObject.randomGameObject(10);
        System.out.println(go);
        assertTrue(go.getNotMarkedPoints().size() > 0);
    }

    @Test
    public void gameFinishTest() {
        GameObject go = new GameObject(10, 5);
        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(0,9),
                new GameObject.Point(1,0),
                new GameObject.Point(2,0),
                new GameObject.Point(3,0),
                new GameObject.Point(3,7),
                new GameObject.Point(39,97)
        ), Boolean.TRUE);
        System.out.println("gameFinishTest 1");
        System.out.println(go);
        assertFalse(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertFalse(go.isGameEnd());

        // horizontal
        go = new GameObject(10, 5);
        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(0,0),
                new GameObject.Point(0,1),
                new GameObject.Point(0,2),
                new GameObject.Point(0,3),
                new GameObject.Point(0,4),
                new GameObject.Point(2,2)
        ), Boolean.FALSE);
        System.out.println("gameFinishTest 2");
        System.out.println(go);
        assertFalse(go.isGameWinnerByTrue());
        assertTrue(go.isGameWinnerByFalse());
        assertTrue(go.isGameEnd());

        //vertical
        go = new GameObject(10, 5);
        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(1,0),
                new GameObject.Point(2,0),
                new GameObject.Point(3,0),
                new GameObject.Point(4,0),
                new GameObject.Point(5,0),
                new GameObject.Point(2,2)
        ), Boolean.TRUE);
        System.out.println("gameFinishTest 3");
        System.out.println(go);
        assertTrue(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertTrue(go.isGameEnd());

        go = new GameObject(10, 5);
        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(1,0),
                new GameObject.Point(2,0),
                new GameObject.Point(3,0),
                new GameObject.Point(5,0),
                new GameObject.Point(6,0),
                new GameObject.Point(2,2)
        ), Boolean.TRUE);
        System.out.println("gameFinishTest 4");
        System.out.println(go);
        assertFalse(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertFalse(go.isGameEnd());

        //left-top to right-bottom diagonal
        go = new GameObject(10, 5);
        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(1,5),
                new GameObject.Point(2,1),
                new GameObject.Point(3,2),
                new GameObject.Point(4,3),
                new GameObject.Point(5,4),
                new GameObject.Point(6,5)
        ), Boolean.TRUE);
        System.out.println("gameFinishTest 5");
        System.out.println(go);
        assertTrue(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertTrue(go.isGameEnd());

        go = new GameObject(10, 5);
        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(1,0),
                new GameObject.Point(2,0),
                new GameObject.Point(3,0),
                new GameObject.Point(5,0),
                new GameObject.Point(6,0),
                new GameObject.Point(2,2)
        ), Boolean.TRUE);
        System.out.println("gameFinishTest 6");
        System.out.println(go);
        assertFalse(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertFalse(go.isGameEnd());

        //right-top to left-bottom diagonal
        go = new GameObject(10, 5);
        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(9,5),
                new GameObject.Point(8,6),
                new GameObject.Point(7,7),
                new GameObject.Point(6,8),
                new GameObject.Point(5,9),
                new GameObject.Point(6,2)
        ), Boolean.TRUE);
        System.out.println("gameFinishTest 7");
        System.out.println(go);
        assertTrue(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertTrue(go.isGameEnd());

        go = new GameObject(10, 5);
        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(1,0),
                new GameObject.Point(2,0),
                new GameObject.Point(3,0),
                new GameObject.Point(5,0),
                new GameObject.Point(6,0),
                new GameObject.Point(2,2)
        ), Boolean.TRUE);
        System.out.println("gameFinishTest 8");
        System.out.println(go);
        assertFalse(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertFalse(go.isGameEnd());
    }


    // same as previous test, but last shift as depends test task
    @Test
    public void gameFinishWithLastShiftTest() throws InvalidPointException {

        GameObject go = new GameObject(10, 5);
        go = go.markAndGet(Arrays.asList(
                new GameObject.Point(9,5),
                new GameObject.Point(8,6),
                new GameObject.Point(6,8),
                new GameObject.Point(5,9),
                new GameObject.Point(6,2)
        ), Boolean.TRUE);
        System.out.println(go);
        assertFalse(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertFalse(go.isGameEnd());

        // For Alice NOOOOOOOOOO
        go = go.markAndGet(new GameObject.Point(0,0), Boolean.TRUE);
        assertFalse(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertFalse(go.isGameEnd());

        // For Alice YESSSSS she have win
        go = go.markAndGet(new GameObject.Point(7,7), Boolean.TRUE);
        assertTrue(go.isGameWinnerByTrue());
        assertFalse(go.isGameWinnerByFalse());
        assertTrue(go.isGameEnd());
    }
}
