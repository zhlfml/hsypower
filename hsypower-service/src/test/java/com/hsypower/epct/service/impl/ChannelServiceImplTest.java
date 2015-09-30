package com.hsypower.epct.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.hsypower.epct.AbstractDBUnitSpringContextTests;
import com.hsypower.epct.entity.Channel;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IChannelService;
import com.hsypower.epct.utils.DateTimeUtils;

public class ChannelServiceImplTest extends AbstractDBUnitSpringContextTests {

	private User user;

	@Autowired
	private IChannelService channelService;

	@Before
	public void setUp() {
		user = new User();
		user.setName("admin");
	}

	@Test
	public void testCreateNewChannel() {
		Channel channel = new Channel();
		channel.setDisplay(true);
		channel.setName("公司简介");
		channel.setPath("/introduce");
		channel.setBottom(true);
		channel.setNavigate(true);
		channel.setLeftSide(true);
		channel.setSort(3);

		channel = channelService.createNewChannel(channel, user);
		assertNotNull(channel.getId());
		assertEquals(user.getName(), channel.getCreatedBy());
		assertNotNull(channel.getCreateOn());
	}

	@Test
	@DatabaseSetup("ChannelServiceImplTest.xml")
	public void testUpdateChannel() {
		Channel channel = channelService.getChannelById(1);
		channel.setName("诚聘英才");
		channel.setPath("/jobs");
		channel.setLeftSide(false);

		channelService.updateChannel(channel, user);
		channel = channelService.getChannelById(1);
		assertEquals("诚聘英才", channel.getName());
		assertEquals("/jobs", channel.getPath());
		assertFalse(channel.isLeftSide());
		assertEquals(user.getName(), channel.getModifiedBy());
		assertNotNull(channel.getModifyOn());
	}

	@Test
	@DatabaseSetup("ChannelServiceImplTest.xml")
	public void testGetChannelById() {
		Channel channel = channelService.getChannelById(1);

		assertNotNull(channel);
		assertEquals("公司简介", channel.getName());
		assertEquals("/introduce", channel.getPath());
		assertEquals("admin", channel.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(channel.getCreateOn()));
		assertNull(channel.getModifiedBy());
		assertNull(channel.getModifyOn());
		assertTrue(channel.isDisplay());
		assertTrue(channel.isNavigate());
		assertTrue(channel.isLeftSide());
		assertTrue(channel.isBottom());
		assertEquals(3, channel.getSort());
	}
	
	@Test
	@DatabaseSetup("ChannelServiceImplTest.xml")
	public void testGetChannelByPath() {
		Channel channel = channelService.getChannelByPath("/introduce");

		assertNotNull(channel);
		assertEquals("公司简介", channel.getName());
		assertEquals("/introduce", channel.getPath());
		assertEquals("admin", channel.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(channel.getCreateOn()));
		assertNull(channel.getModifiedBy());
		assertNull(channel.getModifyOn());
		assertTrue(channel.isDisplay());
		assertTrue(channel.isNavigate());
		assertTrue(channel.isLeftSide());
		assertTrue(channel.isBottom());
		assertEquals(3, channel.getSort());
	}

	@Test
	@DatabaseSetup("ChannelServiceImplTest-position.xml")
	public void testFindAll() {
		int total = 0;
		int lastSort = 0;
		Iterator<Channel> channels = channelService.findAll();
		while (channels.hasNext()) {
			total++;
			int currentSort = channels.next().getSort();
			assertTrue("lastSort = " + lastSort + ", currentSort = "
					+ currentSort, lastSort <= currentSort);
			lastSort = currentSort;
		}

		assertEquals(10, total);
	}

	@Test
	@DatabaseSetup("ChannelServiceImplTest-position.xml")
	public void testFindChannelsAtNavigate() {
		int total = 0;
		int lastSort = 0;
		Iterator<Channel> channels = channelService.findChannelsAtNavigate();
		while (channels.hasNext()) {
			total++;
			int currentSort = channels.next().getSort();
			assertTrue("lastSort = " + lastSort + ", currentSort = "
					+ currentSort, lastSort <= currentSort);
			lastSort = currentSort;
		}

		assertEquals(8, total);
	}

	@Test
	@DatabaseSetup("ChannelServiceImplTest-position.xml")
	public void testFindChannelsAtLeftSide() {
		int total = 0;
		int lastSort = 0;
		Iterator<Channel> channels = channelService.findChannelsAtLeftSide();
		while (channels.hasNext()) {
			total++;
			int currentSort = channels.next().getSort();
			assertTrue("lastSort = " + lastSort + ", currentSort = "
					+ currentSort, lastSort <= currentSort);
			lastSort = currentSort;
		}

		assertEquals(6, total);
	}

	@Test
	@DatabaseSetup("ChannelServiceImplTest-position.xml")
	public void testFindChannelsAtBottom() {
		int total = 0;
		int lastSort = 0;
		Iterator<Channel> channels = channelService.findChannelsAtBottom();
		while (channels.hasNext()) {
			total++;
			int currentSort = channels.next().getSort();
			assertTrue("lastSort = " + lastSort + ", currentSort = "
					+ currentSort, lastSort <= currentSort);
			lastSort = currentSort;
		}

		assertEquals(5, total);
	}

	@Test
	@DatabaseSetup("ChannelServiceImplTest-position.xml")
	public void testCountChannelsAtNavigate() {
		int numOfNavi = channelService.countChannelsAtNavigate();

		assertEquals(8, numOfNavi);
	}
}
