package org.firstinspires.ftc.teamcode.init;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.action.droneSlingshot;
import org.firstinspires.ftc.teamcode.action.mecanumDrive;

@Disabled
@Autonomous(name = "ParkByTime", group = "Main")
public class auto extends OpMode {
    //Timers, code, and all that fun stuff
    public ElapsedTime autoRuntime = new ElapsedTime(); //How long autonomous has been doin' its thing for
    ElapsedTime actionRuntime = new ElapsedTime();
    org.firstinspires.ftc.teamcode.action.mecanumDrive mecanumDrive = new mecanumDrive();
    org.firstinspires.ftc.teamcode.action.droneSlingshot droneSlingshot = new droneSlingshot();
    //Our custom stuff to use here
    int robotAction = 0;
    String teamColor = "White";
    String allianceSide = "The void";
    boolean teamSelected = false;
    boolean sideSelected = false;
    boolean throughMiddle = false;

    public void init() {
        mecanumDrive.init(this);
        mecanumDrive.runWithoutEncoder();
        droneSlingshot.init(this);
    }

    public void init_loop() {
        /* This loops during init period. This allows the primary driver to tell the robot what
         * alliance and side the robot is on. This allows us to keep all starting positions and parking
         * code all in one place, because nobody wants a mess of code that looks more like spaghetti
         * than something that was written by an actual human being. */

        if (gamepad1.x) { // If the blue button is pushed on the controller
            teamColor = "Blue";
        }else if(gamepad1.b) { //If the red button is pushed on the controller
            teamColor = "Red";
        }else if(gamepad1.y) { //If the Y button is pressed
            teamSelected = true;
        }else if(gamepad1.dpad_left) {
            allianceSide = "Left";
        }else if(gamepad1.dpad_right) {
            allianceSide = "Right";
        }else if(gamepad1.dpad_up)  {
            sideSelected = true;
        }

        if(gamepad1.dpad_down) {
            throughMiddle = true;
            telemetry.addData("Status", "Middle selected");
        }


        if(teamSelected && sideSelected){
            telemetry.addData("Status", "You can now make robot go brrrrrrr");
        }else { //If side and alliance have not been selected
            if (teamColor.equals("Blue")) { //If team color is blue
                telemetry.addData("Team Alliance", "You are on the blue alliance. Press B to become red alliance");
            }else if(teamColor.equals("Red")) {//If selected team color is red
                telemetry.addData("Team Selected", "You are on red alliance. Press X to become blue alliance");
            }else if(teamSelected){
                telemetry.addData("Team Selected", "You have chosen an alliance!");
            }else{ //If no alliance is chosen
                telemetry.addData("Team Selected", "You are on neither alliance. Press B or X to join one");
            }
            if(allianceSide.equals("Left")){ //If selected side is left
                telemetry.addData("Side Selected", "You are on the left side. Press right on the Dpad to be on the right side");
            }else if(allianceSide.equals("Right")){ //If the selected side is right
                telemetry.addData("Side Selected", "You are on the right side. Press right on the Dpad to be on the left side");
            }else if(sideSelected){
                telemetry.addData("Side Selected", "You have chosen your side!");
            }else{ //If no side is selected
                telemetry.addData("Side Selected", "You exist in the void! Press right or left on the Dpad to choose a side");
            }
        }
    }

    public void start() {
        autoRuntime.reset();
        actionRuntime.reset();
    }

    @Override
    public void loop() {
        // Mode Selector
        int ModeToPerform = 0;
        if(!throughMiddle) {
            if(teamColor.equals("Blue") && allianceSide.equals("Right")){
                ModeToPerform = 1;
            }else if(teamColor.equals("Blue") && allianceSide.equals("Left")){
                ModeToPerform = 2;
            }else if(teamColor.equals("Red") && allianceSide.equals("Right")){
                ModeToPerform = 3;
            }else if(teamColor.equals("Red") && allianceSide.equals("Left")){
                ModeToPerform = 4;
            }
        }else {
            if(teamColor.equals("Blue") && allianceSide.equals("Right")){
                ModeToPerform = 5;
            }else if(teamColor.equals("Blue") && allianceSide.equals("Left")){
                ModeToPerform = 6;
            }else if(teamColor.equals("Red") && allianceSide.equals("Right")){
                ModeToPerform = 7;
            }else if(teamColor.equals("Red") && allianceSide.equals("Left")){
                ModeToPerform = 8;
            }
        }

        //if(teamColor.equals("Blue") && allianceSide.equals("Right")){
        if(ModeToPerform == 1){
            if(robotAction == 0) {
                mecanumDrive(0.1, 1, 0, 0);
            }else if(robotAction == 1) {
                waitThenGoToNextAction(0.2);
            }else if(robotAction == 2) {
                mecanumDrive(2.4, 0, -1, 0);
            }
        }

        //if(teamColor.equals("Blue") && allianceSide.equals("Left")){
        if(ModeToPerform == 2){
            if(robotAction == 0) {
                mecanumDrive(0.1, 1, 0, 0);
            }else if(robotAction == 1) {
                waitThenGoToNextAction(0.2);
            }else if(robotAction == 2) {
                mecanumDrive(1.2, 0, -1, 0);
            }
        }

        //if(teamColor.equals("Red") && allianceSide.equals("Left")){
        if(ModeToPerform == 4){
                if(robotAction == 0) {
                mecanumDrive(0.1, 1, 0, 0);
            }else if(robotAction == 1) {
                waitThenGoToNextAction(0.2);
            }else if(robotAction == 2) {
                mecanumDrive(2.4, 0, 1, 0);
            }
        }

        //if(teamColor.equals("Red") && allianceSide.equals("Right")){
        if(ModeToPerform == 3){
            if(robotAction == 0) {
                mecanumDrive(0.1, 1, 0, 0);
            }else if(robotAction == 1) {
                waitThenGoToNextAction(0.2);
            }else if(robotAction == 2) {
                mecanumDrive(1.2, 0, 1, 0);
            }
        }

        // Finish this for each ModeToPerform 5-8
        if(ModeToPerform == 5 || ModeToPerform == 6 || ModeToPerform == 7 || ModeToPerform == 8){
            if (robotAction == 0) {
                mecanumDrive(1, 1, 0, 0);
            }else if (robotAction == 1) {
                waitThenGoToNextAction(0.2);
            }else if (robotAction == 2) {
                mecanumDrive(2.4, 0, -1, 0);
            }
        }

    }

    /**
     * This lets our code know that we want a motor power applied to motors in a specific way, and
     * this OpMode will keep track of the time.
     * @param secondsToRunFor is how long this OpMode needs to keep track of.
     * @param yAxisPower is how fast forward and back we want to go.
     * @param xAxisPower is how fast to the left or right we want to strafe.
     * @param rotation is how fast clockwise or counter-clockwise we want to turn.
     */
    public void mecanumDrive(double secondsToRunFor, double yAxisPower, double xAxisPower, double rotation) {
            mecanumDrive.slowMode(true); //Despite what it says, this will make the robot go at max speed
            yAxisPower = yAxisPower * -1;
            mecanumDrive.setPower(xAxisPower, yAxisPower, rotation);
            if(actionRuntime.time() >= secondsToRunFor) {
                robotAction++; //moves to next action
                actionRuntime.reset(); //resets timer
                mecanumDrive.setPower(0, 0, 0); //stops the motors
            }
    }

    /**
     * This is a custom-built wait() command. We have to have this, because OpModes do not like to
     * looping.
     * @param secondsToWait is our value to wait for,
     */
    public void waitThenGoToNextAction(double secondsToWait) {
        if(actionRuntime.time() >= secondsToWait) {
            robotAction++; //moves to next action
            actionRuntime.reset(); //resets timer
        }
    }
}