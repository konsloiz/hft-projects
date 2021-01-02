package test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;


import definitions.Radio;
import definitions.Socket;
import definitions.TooLoud;
import definitions.WrongVoltage;
import devices.GrundigRadio;

public class TestRadio {
	
	private Radio radio;

	@BeforeEach
	public void setUp() throws Exception {
		 radio = new GrundigRadio();
	}

	@Test
	public void testConnectEU() throws WrongVoltage {
		radio.connect(Socket.EU);
	}

	@Test
	public void testConnectUS() throws WrongVoltage {
		assertThrows(WrongVoltage.class, () -> radio.connect(Socket.US));
	}

	@Test
	public void testIsOn1() throws WrongVoltage {
		assertFalse(radio.isOn());
		assertTrue(radio.isOff());
	}

	@Test
	public void testIsOn2() throws WrongVoltage {
		radio.connect(Socket.EU);
		assertFalse(radio.isOn());
		assertTrue(radio.isOff());
	}

	@Test
	public void testIsOn3() throws WrongVoltage {
		radio.connect(Socket.EU);
		radio.onOff();
		assertTrue(radio.isOn());
		assertFalse(radio.isOff());
	}

	@Test
	public void testOnOff1() throws WrongVoltage {
		radio.onOff();
		assertFalse(radio.isOn());
	}

	@Test
	public void testOnOff2() throws WrongVoltage {
		radio.onOff();
		radio.onOff();
		assertFalse(radio.isOn());
	}

	@Test
	public void testOnOff3() throws WrongVoltage {
		radio.connect(Socket.EU);
		radio.onOff();
		radio.onOff();
		assertFalse(radio.isOn());
	}

	@Test
	public void testOnOff4() throws WrongVoltage {
		radio.connect(Socket.EU);
		radio.onOff();
		radio.onOff();
		radio.onOff();
		assertTrue(radio.isOn());
	}

	@Test
	public void turnUp1() throws WrongVoltage {
		radio.connect(Socket.EU);
		radio.turnUp();
		assertEquals(0, radio.getVolume());
	}

	@Test
	public void turnUp2() throws WrongVoltage {
		radio.connect(Socket.EU);
		radio.onOff();
		radio.turnUp();
		assertEquals(1, radio.getVolume());
	}

	@Test
	public void turnUp3() throws WrongVoltage {
		radio.connect(Socket.EU);
		radio.onOff();
		assertThrows(TooLoud.class, () -> {
			for(int i = 0; i < 105; i++)
				radio.turnUp();
		});

	}

	@Test
	public void turnDown1() throws WrongVoltage {
		radio.connect(Socket.EU);
		radio.turnDown();
		assertEquals(0, radio.getVolume());
	}

	@Test
	public void turnDown2() throws WrongVoltage {
		radio.connect(Socket.EU);
		radio.onOff();
		radio.turnDown();
		assertEquals(0, radio.getVolume());
	}


	@Test
	public void turnDown3() throws WrongVoltage {
		radio.connect(Socket.EU);
		radio.onOff();
		int steps = 20;
		for(int i = 0; i < steps; i++)
			radio.turnUp();
		radio.turnDown();
		assertEquals(steps - 1, radio.getVolume());
	}
}
